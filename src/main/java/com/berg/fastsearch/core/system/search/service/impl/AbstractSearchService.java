package com.berg.fastsearch.core.system.search.service.impl;

import com.berg.fastsearch.core.system.base.web.dto.BaseQueryCondition;
import com.berg.fastsearch.core.system.base.entity.ServiceMultiResult;
import com.berg.fastsearch.core.system.kafka.service.IKafkaService;
import com.berg.fastsearch.core.system.search.service.ISearchService;
import com.berg.fastsearch.core.system.kafka.message.BaseIndexMessage;
import com.berg.fastsearch.core.system.search.template.BaseTemplate;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.DeleteByQueryAction;
import org.elasticsearch.index.reindex.DeleteByQueryRequestBuilder;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <p></p>
 *
 * @author bergturing@qq.com
 * @version v1.0
 * @apiNote Created on 18-5-7
 */
@Service
public abstract class AbstractSearchService<
        ID extends Serializable,
        CONDITION extends BaseQueryCondition,
        MESSAGE extends BaseIndexMessage<ID>,
        TEMPLATE extends BaseTemplate> implements ISearchService<ID, MESSAGE, CONDITION> {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private static final String INDEX_TOPIC = "car_build";


    @Autowired
    private TransportClient esClient;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private IKafkaService kafkaService;

    @Override
    public void processMessage(String content) {
        MESSAGE message = null;
        try {
            message = objectMapper.readValue(content, getMessageClass());

            switch (message.getOperation()) {
                case BaseIndexMessage.INDEX:
                    this.createOrUpdateIndex(message);
                    break;
                case BaseIndexMessage.REMOVE:
                    this.removeIndex(message);
                    break;
                default:
                    logger.warn("Not support message content " + message);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createOrUpdateIndex(MESSAGE message) throws Exception {
        ID id = message.getId();

        TEMPLATE template = map(id);

        SearchRequestBuilder requestBuilder = this.esClient.prepareSearch(getIndexName()).setTypes(getTypeName())
                .setQuery(QueryBuilders.termQuery(BaseTemplate.FIELD_ID, id));

        logger.debug(requestBuilder.toString());
        SearchResponse searchResponse = requestBuilder.get();

        boolean success;
        long totalHit = searchResponse.getHits().getTotalHits();
        if (totalHit == 0) {
            success = create(template);
        } else if (totalHit == 1) {
            String esId = searchResponse.getHits().getAt(0).getId();
            success = update(esId, template);
        } else {
            success = deleteAndCreate(totalHit, template);
        }
    }

    private boolean create(TEMPLATE template) {
        try {
            IndexResponse response = this.esClient.prepareIndex(getIndexName(), getTypeName())
                    .setSource(objectMapper.writeValueAsBytes(template), XContentType.JSON).get();

            if (response.status() == RestStatus.CREATED) {
                return true;
            } else {
                return false;
            }
        } catch (JsonProcessingException e) {
            logger.error("Error to index house " + template.getId(), e);
            return false;
        }
    }

    private boolean update(String id, TEMPLATE template) {
        try {
            UpdateResponse response = this.esClient.prepareUpdate(getIndexName(), getTypeName(), id)
                    .setDoc(objectMapper.writeValueAsBytes(template), XContentType.JSON).get();

            logger.debug("Update index with house: " + template.getId());
            if (response.status() == RestStatus.OK) {
                return true;
            } else {
                return false;
            }
        } catch (JsonProcessingException e) {
            logger.error("Error to index house " + template.getId(), e);
            return false;
        }
    }

    private boolean deleteAndCreate(long totalHit, TEMPLATE template) {
        DeleteByQueryRequestBuilder builder = DeleteByQueryAction.INSTANCE
                .newRequestBuilder(esClient)
                .filter(QueryBuilders.termQuery(BaseTemplate.FIELD_ID, template.getId()))
                .source(getIndexName());

        logger.debug("Delete by query for house: " + builder);

        BulkByScrollResponse response = builder.get();
        long deleted = response.getDeleted();
        if (deleted != totalHit) {
            logger.warn("Need delete {}, but {} was deleted!", totalHit, deleted);
            return false;
        } else {
            return create(template);
        }
    }

    private void removeIndex(MESSAGE message) {
        ID id = message.getId();
        DeleteByQueryRequestBuilder builder = DeleteByQueryAction.INSTANCE
                .newRequestBuilder(esClient)
                .filter(QueryBuilders.termQuery(BaseTemplate.FIELD_ID, id))
                .source(getIndexName());

        logger.debug("Delete by query for house: " + builder);

        BulkByScrollResponse response = builder.get();
        long deleted = response.getDeleted();
        logger.debug("Delete total " + deleted);
    }

    @Override
    public final void index(ID id) {
        try {
            index(id, BaseIndexMessage.MAX_RETRY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public final void remove(ID id) {
        try {
            remove(id, BaseIndexMessage.MAX_RETRY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public final ServiceMultiResult<ID> query(CONDITION condition) {
        List<ID> list = new ArrayList<>();

        SearchRequestBuilder searchRequestBuilder = this.esClient.prepareSearch(getIndexName()).setTypes(getTypeName());

        //如果查询条件不为空,就构建查询条件
        if(condition != null){
            //页码与分页大小
            Integer start = condition.getStart();
            Integer size = condition.getSize();
            if(start!=null && start>0 && size!=null && size>0){
                searchRequestBuilder = searchRequestBuilder
                        .setFrom(start)
                        .setSize(size);
            }

            //子类实现的构建的其他查询条件
            buildRequest(searchRequestBuilder, condition);
        }

        //开始执行查询
        logger.debug(searchRequestBuilder.toString());
        SearchResponse searchResponse = searchRequestBuilder.get();

        //查询的结果
        SearchHit[] hits = searchResponse.getHits().getHits();

        //获取结果
        for (SearchHit searchHitFields : hits){
            Serializable id = objectMapper.convertValue(searchHitFields.getSource(), getTemplateClass()).getId();

            if(id != null){
                list.add((ID)id);
            }
        }

        return new ServiceMultiResult<ID>(searchResponse.getHits().getTotalHits(), list);
    }

    /**
     *
     * @param id
     * @param retry
     */
    private void index(ID id, Integer retry) throws Exception {
        if (retry > BaseIndexMessage.MAX_RETRY) {
            logger.error("Retry index times over 3 for id: " + id + " Please check it!");
            return;
        }

        MESSAGE message = getMessage();
        message.setId(id);
        message.setOperation(BaseIndexMessage.INDEX);
        message.setRetry(retry);
        try {
            kafkaService.send(message);
        } catch (JsonProcessingException e) {
            logger.error("Json encode error for " + message);
        }

    }

    /**
     *
     * @param id
     * @param retry
     */
    private void remove(ID id, Integer retry) throws Exception {
        if (retry > BaseIndexMessage.MAX_RETRY) {
            logger.error("Retry index times over 3 for id: " + id + " Please check it!");
            return;
        }

        MESSAGE message = getMessage();
        message.setId(id);
        message.setOperation(BaseIndexMessage.REMOVE);
        message.setRetry(retry);
        try {
            kafkaService.send(message);
        } catch (JsonProcessingException e) {
            logger.error("Json encode error for " + message);
        }

    }

    /**
     * 根据指定的主键,构建es模板对象
     * @param id    主键
     * @return      es模板对象
     */
    protected abstract TEMPLATE map(ID id) throws Exception;

    /**
     * 获取模板对象的类
     * @return  模板对象的类
     */
    protected abstract Class<TEMPLATE> getTemplateClass();

    /**
     * 获取message对象
     * @return  message对象
     */
    protected abstract MESSAGE getMessage();

    /**
     *
     * @return
     */
    protected abstract Class<MESSAGE> getMessageClass();

    /**
     * 构建请求对象
     * @param searchRequestBuilder  请求对象
     * @param condition             查询条件
     */
    protected abstract void buildRequest(final SearchRequestBuilder searchRequestBuilder, final CONDITION condition);

    /**
     * 获取索引名
     * @return  返回索引名
     */
    protected abstract String getIndexName();

    /**
     * 获取类型名
     * @return  返回类型名
     */
    protected abstract String getTypeName();
}

package com.berg.fastsearch.core.system.search.service.impl;

import com.berg.fastsearch.core.system.base.web.dto.BaseQueryCondition;
import com.berg.fastsearch.core.system.search.service.ISearchService;
import com.berg.fastsearch.core.system.search.support.BaseIndexMessage;
import com.berg.fastsearch.core.system.search.template.BaseTemplate;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.admin.indices.analyze.AnalyzeAction;
import org.elasticsearch.action.admin.indices.analyze.AnalyzeRequestBuilder;
import org.elasticsearch.action.admin.indices.analyze.AnalyzeResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.DeleteByQueryAction;
import org.elasticsearch.index.reindex.DeleteByQueryRequestBuilder;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.sort.SortBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <p></p>
 *
 * @author bo.he02@hand-china.com
 * @version v1.0
 * @apiNote Created on 18-5-7
 */
@Service
public abstract class AbstractSearchService<
        ID extends Serializable,
        CONDITION extends BaseQueryCondition,
        MESSAGE extends BaseIndexMessage<ID>,
        TEMPLATE extends BaseTemplate> implements ISearchService<ID, CONDITION> {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private static final String INDEX_TOPIC = "car_build";


    @Autowired
    private TransportClient esClient;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @KafkaListener(topics = INDEX_TOPIC)
    private void handleMessage(String content) {
        try {
            MESSAGE message = objectMapper.readValue(content, getIndexMessageClass());

            switch (message.getOperation()) {
                case BaseIndexMessage.INDEX:
                    this.createOrUpdateIndex(message);
                    break;
                case BaseIndexMessage.REMOVE:
                    this.removeIndex(message);
                    break;
                default:
                    logger.warn("Not support message content " + content);
                    break;
            }
        } catch (IOException e) {
            logger.error("Cannot parse json for " + content, e);
        }
    }

    private void createOrUpdateIndex(MESSAGE message) {
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

//        ServiceResult serviceResult = addressService.lbsUpload(location.getResult(), house.getStreet() + house.getDistrict(),
//                city.getCnName() + region.getCnName() + house.getStreet() + house.getDistrict(),
//                message.getHouseId(), house.getPrice(), house.getArea());
//
//        if (!success || !serviceResult.isSuccess()) {
//            this.index(message.getHouseId(), message.getRetry() + 1);
//        } else {
//            logger.debug("Index success with house " + houseId);
//
//        }
    }

    private boolean create(TEMPLATE template) {
        try {
            IndexResponse response = this.esClient.prepareIndex(getIndexName(), getTypeName())
                    .setSource(objectMapper.writeValueAsBytes(template), XContentType.JSON).get();

//            logger.debug("Create index with house: " + template.getId());
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
    public final List<ID> query(CONDITION condition) {
        List<ID> list = new ArrayList<>();

        SearchRequestBuilder searchRequestBuilder = this.esClient.prepareSearch(getIndexName()).setTypes(getTypeName());

        //如果查询条件不为空,就构建查询条件
        if(condition != null){
            //页码与分页大小
            Integer page = condition.getPage();
            Integer pageSize = condition.getPageSize();
            if(page!=null && page>0 && pageSize!=null && pageSize>0){
                searchRequestBuilder = searchRequestBuilder
                        .setFrom((page-1)*pageSize)
                        .setSize(pageSize);
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
            list.add(objectMapper.convertValue(searchHitFields.getSource(), getIndexMessageClass()).getId());
        }

        return list;
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

        MESSAGE message = getIndexMessageClass().newInstance();
        message.init(id, BaseIndexMessage.INDEX, retry);
        try {
            kafkaTemplate.send(INDEX_TOPIC, objectMapper.writeValueAsString(message));
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

        MESSAGE message = getIndexMessageClass().newInstance();
        message.init(id, BaseIndexMessage.REMOVE, retry);
        try {
            kafkaTemplate.send(INDEX_TOPIC, objectMapper.writeValueAsString(message));
        } catch (JsonProcessingException e) {
            logger.error("Json encode error for " + message);
        }

    }

    /**
     * 根据指定的主键,构建es模板对象
     * @param id    主键
     * @return      es模板对象
     */
    protected abstract TEMPLATE map(ID id);

    /**
     *
     * @return
     */
    protected abstract Class<MESSAGE> getIndexMessageClass();

    /**
     * 构建请求对象
     * @param searchRequestBuilder  请求对象
     * @param condition             查询条件
     * @return                      构建的查询对象
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

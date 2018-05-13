package com.berg.fastsearch.core.system.search.service.impl;

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
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.DeleteByQueryAction;
import org.elasticsearch.index.reindex.DeleteByQueryRequestBuilder;
import org.elasticsearch.rest.RestStatus;
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
        MESSAGE extends BaseIndexMessage<ID>,
        TEMPLATE extends BaseTemplate> implements ISearchService<ID> {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private static final String INDEX_NAME = "fastsearch";

    private static final String INDEX_TYPE = "car";

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

        SearchRequestBuilder requestBuilder = this.esClient.prepareSearch(INDEX_NAME).setTypes(INDEX_TYPE)
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
        if (!updateSuggest(template)) {
            return false;
        }

        try {
            IndexResponse response = this.esClient.prepareIndex(INDEX_NAME, INDEX_TYPE)
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
        if (!updateSuggest(template)) {
            return false;
        }

        try {
            UpdateResponse response = this.esClient.prepareUpdate(INDEX_NAME, INDEX_TYPE, id)
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

    private boolean updateSuggest(TEMPLATE template) {
//        AnalyzeRequestBuilder requestBuilder = new AnalyzeRequestBuilder(
//                this.esClient, AnalyzeAction.INSTANCE, INDEX_NAME, indexTemplate.getTitle(),
//                indexTemplate.getLayoutDesc(), indexTemplate.getRoundService(),
//                indexTemplate.getDescription(), indexTemplate.getSubwayLineName(),
//                indexTemplate.getSubwayStationName());
//
//        requestBuilder.setAnalyzer("ik_smart");
//
//        AnalyzeResponse response = requestBuilder.get();
//        List<AnalyzeResponse.AnalyzeToken> tokens = response.getTokens();
//        if (tokens == null) {
//            logger.warn("Can not analyze token for house: " + indexTemplate.getHouseId());
//            return false;
//        }
//
//        List<HouseSuggest> suggests = new ArrayList<>();
//        for (AnalyzeResponse.AnalyzeToken token : tokens) {
//            // 排序数字类型 & 小于2个字符的分词结果
//            if ("<NUM>".equals(token.getType()) || token.getTerm().length() < 2) {
//                continue;
//            }
//
//            HouseSuggest suggest = new HouseSuggest();
//            suggest.setInput(token.getTerm());
//            suggests.add(suggest);
//        }
//
//        // 定制化小区自动补全
//        HouseSuggest suggest = new HouseSuggest();
//        suggest.setInput(indexTemplate.getDistrict());
//        suggests.add(suggest);
//
//        indexTemplate.setSuggest(suggests);
        return true;
    }

    private boolean deleteAndCreate(long totalHit, TEMPLATE template) {
        DeleteByQueryRequestBuilder builder = DeleteByQueryAction.INSTANCE
                .newRequestBuilder(esClient)
                .filter(QueryBuilders.termQuery(BaseTemplate.FIELD_ID, template.getId()))
                .source(INDEX_NAME);

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
                .source(INDEX_NAME);

        logger.debug("Delete by query for house: " + builder);

        BulkByScrollResponse response = builder.get();
        long deleted = response.getDeleted();
        logger.debug("Delete total " + deleted);

//        ServiceResult serviceResult = addressService.removeLbs(houseId);
//
//        if (!serviceResult.isSuccess() || deleted <= 0) {
//            logger.warn("Did not remove data from es for response: " + response);
//            // 重新加入消息队列
//            this.remove(houseId, message.getRetry() + 1);
//        }
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

}

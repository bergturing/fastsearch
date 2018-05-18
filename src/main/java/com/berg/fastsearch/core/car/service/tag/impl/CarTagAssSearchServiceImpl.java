package com.berg.fastsearch.core.car.service.tag.impl;

import com.berg.fastsearch.core.car.service.tag.ICarTagAssSearchService;
import com.berg.fastsearch.core.car.service.tag.ICarTagAssService;
import com.berg.fastsearch.core.car.web.dto.tag.CarTagAssIndexMessage;
import com.berg.fastsearch.core.car.web.dto.tag.CarTagAssQueryCondition;
import com.berg.fastsearch.core.car.web.dto.tag.CarTagAssTemplate;
import com.berg.fastsearch.core.system.search.service.impl.AbstractSearchService;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p></p>
 *
 * @author bo.he02@hand-china.com
 * @version v1.0
 * @apiNote Created on 18-5-17
 */
@Service
public class CarTagAssSearchServiceImpl
        extends AbstractSearchService<Long, CarTagAssQueryCondition, CarTagAssIndexMessage, CarTagAssTemplate>
        implements ICarTagAssSearchService{

    @Autowired
    private ICarTagAssService carTagAssService;

    @Override
    protected CarTagAssTemplate map(Long id) {
        CarTagAssTemplate template = new CarTagAssTemplate();

        BeanUtils.copyProperties(carTagAssService.findOne(id), template);

        return template;
    }

    @Override
    protected Class<CarTagAssTemplate> getTemplateClass() {
        return CarTagAssTemplate.class;
    }

    @Override
    protected CarTagAssIndexMessage getMessage() {
        CarTagAssIndexMessage message = new CarTagAssIndexMessage();
        message.setProcessServiceName("carTagAssSearchServiceImpl");
        return message;
    }

    @Override
    protected Class<CarTagAssIndexMessage> getMessageClass() {
        return CarTagAssIndexMessage.class;
    }

    @Override
    protected void buildRequest(SearchRequestBuilder searchRequestBuilder, CarTagAssQueryCondition condition) {
        BoolQueryBuilder builder = QueryBuilders.boolQuery();

        Long id = condition.getId();
        if(id!=null && id>0){
            builder = builder
                    .must(QueryBuilders.termQuery(CarTagAssQueryCondition.FIELD_ID, id));
        }

        Long carId = condition.getCarId();
        if(carId!=null && carId>0){
            builder = builder
                    .must(QueryBuilders.termQuery(CarTagAssQueryCondition.FIELD_CAR_ID, carId));
        }

        Long carTagId = condition.getCarTagId();
        if(carTagId!=null && carTagId>0){
            builder = builder
                    .must(QueryBuilders.termQuery(CarTagAssQueryCondition.FIELD_CAR_TAG_ID, carTagId));
        }

        searchRequestBuilder.setQuery(builder);
    }

    @Override
    protected String getIndexName() {
        return "cartagass";
    }

    @Override
    protected String getTypeName() {
        return "cartagass";
    }
}

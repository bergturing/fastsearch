package com.berg.fastsearch.core.car.service.tag.impl;

import com.berg.fastsearch.core.car.service.tag.ICarTagSearchService;
import com.berg.fastsearch.core.car.service.tag.ICarTagService;
import com.berg.fastsearch.core.car.web.dto.tag.CarTagIndexMessage;
import com.berg.fastsearch.core.car.web.dto.tag.CarTagQueryCondition;
import com.berg.fastsearch.core.car.web.dto.tag.CarTagTemplate;
import com.berg.fastsearch.core.system.search.service.impl.AbstractSearchService;
import org.apache.commons.lang.StringUtils;
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
public class CarTagSearchServiceImpl
        extends AbstractSearchService<Long, CarTagQueryCondition, CarTagIndexMessage, CarTagTemplate>
        implements ICarTagSearchService{

    @Autowired
    private ICarTagService carTagService;

    @Override
    protected CarTagTemplate map(Long id) {
        CarTagTemplate template = new CarTagTemplate();

        BeanUtils.copyProperties(carTagService.findOne(id), template);

        return template;
    }

    @Override
    protected Class<CarTagTemplate> getTemplateClass() {
        return CarTagTemplate.class;
    }

    @Override
    protected CarTagIndexMessage getMessage() {
        CarTagIndexMessage message = new CarTagIndexMessage();
        message.setProcessServiceName("carTagSearchServiceImpl");
        return message;
    }

    @Override
    protected Class<CarTagIndexMessage> getMessageClass() {
        return CarTagIndexMessage.class;
    }

    @Override
    protected void buildRequest(SearchRequestBuilder searchRequestBuilder, CarTagQueryCondition condition) {
        BoolQueryBuilder builder = QueryBuilders.boolQuery();

        Long id = condition.getId();
        if(id!=null && id>0){
            builder = builder
                    .must(QueryBuilders.termQuery(CarTagQueryCondition.FIELD_ID, id));
        }

        String name = condition.getName();
        if(StringUtils.isNotBlank(name)){
            builder = builder
                    .must(QueryBuilders.termQuery(CarTagQueryCondition.FIELD_NAME, name));
        }

        searchRequestBuilder.setQuery(builder);
    }

    @Override
    protected String getIndexName() {
        return "cartag";
    }

    @Override
    protected String getTypeName() {
        return "cartag";
    }
}

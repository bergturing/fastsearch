package com.berg.fastsearch.core.car.service.brand.impl;

import com.berg.fastsearch.core.car.service.brand.ICarBrandSearchService;
import com.berg.fastsearch.core.car.service.brand.ICarBrandService;
import com.berg.fastsearch.core.car.web.dto.brand.CarBrandIndexMessage;
import com.berg.fastsearch.core.car.web.dto.brand.CarBrandQueryCondition;
import com.berg.fastsearch.core.car.web.dto.brand.CarBrandTemplate;
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
 * @apiNote Created on 18-5-16
 */
@Service
public class CarBrandSearchServiceImpl
        extends AbstractSearchService<Long, CarBrandQueryCondition, CarBrandIndexMessage, CarBrandTemplate>
        implements ICarBrandSearchService{

    @Autowired
    private ICarBrandService carBrandService;

    @Override
    protected CarBrandTemplate map(Long id) {
        CarBrandTemplate template = new CarBrandTemplate();

        BeanUtils.copyProperties(carBrandService.findOne(id), template);

        return template;
    }

    @Override
    protected Class<CarBrandTemplate> getTemplateClass() {
        return CarBrandTemplate.class;
    }

    @Override
    protected CarBrandIndexMessage getMessage() {
        CarBrandIndexMessage message = new CarBrandIndexMessage();
        message.setProcessServiceName("carBrandSearchServiceImpl");
        return message;
    }

    @Override
    protected Class<CarBrandIndexMessage> getMessageClass() {
        return CarBrandIndexMessage.class;
    }

    @Override
    protected void buildRequest(SearchRequestBuilder searchRequestBuilder, CarBrandQueryCondition condition) {
        BoolQueryBuilder builder = QueryBuilders.boolQuery();

        Long id = condition.getId();
        if(id!=null && id>0){
            builder = builder
                    .must(QueryBuilders.termQuery(CarBrandQueryCondition.FIELD_ID, id));
        }

        String code = condition.getCode();
        if(StringUtils.isNotBlank(code)){
            builder = builder
                    .must(QueryBuilders.termQuery(CarBrandQueryCondition.FIELD_CODE, code));
        }

        String name = condition.getName();
        if(StringUtils.isNotBlank(name)){
            builder = builder
                    .must(QueryBuilders.termQuery(CarBrandQueryCondition.FIELD_NAME, name));
        }

        searchRequestBuilder.setQuery(builder);
    }

    @Override
    protected String getIndexName() {
        return "carbrand";
    }

    @Override
    protected String getTypeName() {
        return "carbrand";
    }
}

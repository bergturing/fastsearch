package com.berg.fastsearch.core.car.service.series.impl;

import com.berg.fastsearch.core.car.service.series.ICarSeriesSearchSerivce;
import com.berg.fastsearch.core.car.service.series.ICarSeriesService;
import com.berg.fastsearch.core.car.web.dto.series.CarSeriesIndexMessage;
import com.berg.fastsearch.core.car.web.dto.series.CarSeriesQueryCondition;
import com.berg.fastsearch.core.car.web.dto.series.CarSeriesTemplate;
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
public class CarSeriesSearchSerivceImpl
        extends AbstractSearchService<Long, CarSeriesQueryCondition, CarSeriesIndexMessage, CarSeriesTemplate>
        implements ICarSeriesSearchSerivce{

    @Autowired
    private ICarSeriesService carSeriesService;

    @Override
    protected CarSeriesTemplate map(Long id) {
        CarSeriesTemplate template = new CarSeriesTemplate();

        BeanUtils.copyProperties(carSeriesService.findOne(id), template);

        return template;
    }

    @Override
    protected Class<CarSeriesTemplate> getTemplateClass() {
        return CarSeriesTemplate.class;
    }

    @Override
    protected CarSeriesIndexMessage getMessage() {
        CarSeriesIndexMessage message = new CarSeriesIndexMessage();
        message.setProcessServiceName("carSeriesSearchSerivceImpl");
        return message;
    }

    @Override
    protected Class<CarSeriesIndexMessage> getMessageClass() {
        return CarSeriesIndexMessage.class;
    }

    @Override
    protected void buildRequest(SearchRequestBuilder searchRequestBuilder, CarSeriesQueryCondition condition) {
        BoolQueryBuilder builder = QueryBuilders.boolQuery();

        Long id = condition.getId();
        if(id!=null && id>0){
            builder = builder
                    .must(QueryBuilders.termQuery(CarSeriesQueryCondition.FIELD_ID, id));
        }

        Long brandId = condition.getBrandId();
        if(brandId!=null && brandId>0){
            builder = builder
                    .must(QueryBuilders.termQuery(CarSeriesQueryCondition.FIELD_BRAND_ID, brandId));
        }

        String code = condition.getCode();
        if(StringUtils.isNotBlank(code)){
            builder = builder
                    .must(QueryBuilders.termQuery(CarSeriesQueryCondition.FIELD_CODE, code));
        }

        String name = condition.getName();
        if(StringUtils.isNotBlank(name)){
            builder = builder
                    .must(QueryBuilders.termQuery(CarSeriesQueryCondition.FIELD_NAME, name));
        }

        searchRequestBuilder.setQuery(builder);
    }

    @Override
    protected String getIndexName() {
        return "carseries";
    }

    @Override
    protected String getTypeName() {
        return "carseries";
    }
}

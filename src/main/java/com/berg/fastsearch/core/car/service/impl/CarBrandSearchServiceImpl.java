package com.berg.fastsearch.core.car.service.impl;

import com.berg.fastsearch.core.car.service.ICarBrandSearchService;
import com.berg.fastsearch.core.car.service.ICarBrandService;
import com.berg.fastsearch.core.car.web.dto.CarBrandIndexMessage;
import com.berg.fastsearch.core.car.web.dto.CarBrandQueryCondition;
import com.berg.fastsearch.core.car.web.dto.CarBrandTemplate;
import com.berg.fastsearch.core.system.search.service.impl.AbstractSearchService;
import org.elasticsearch.action.search.SearchRequestBuilder;
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
    protected Class<CarBrandIndexMessage> getIndexMessageClass() {
        return CarBrandIndexMessage.class;
    }

    @Override
    protected void buildRequest(SearchRequestBuilder searchRequestBuilder, CarBrandQueryCondition condition) {

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

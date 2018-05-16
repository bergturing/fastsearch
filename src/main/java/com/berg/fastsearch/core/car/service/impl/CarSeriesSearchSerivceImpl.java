package com.berg.fastsearch.core.car.service.impl;

import com.berg.fastsearch.core.car.service.ICarSeriesSearchSerivce;
import com.berg.fastsearch.core.car.service.ICarSeriesService;
import com.berg.fastsearch.core.car.web.dto.CarSeriesIndexMessage;
import com.berg.fastsearch.core.car.web.dto.CarSeriesQueryCondition;
import com.berg.fastsearch.core.car.web.dto.CarSeriesTemplate;
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
    protected Class<CarSeriesIndexMessage> getIndexMessageClass() {
        return CarSeriesIndexMessage.class;
    }

    @Override
    protected void buildRequest(SearchRequestBuilder searchRequestBuilder, CarSeriesQueryCondition condition) {

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

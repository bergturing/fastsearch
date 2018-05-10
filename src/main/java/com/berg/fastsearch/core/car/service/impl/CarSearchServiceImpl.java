package com.berg.fastsearch.core.car.service.impl;

import com.berg.fastsearch.core.car.entity.CarIndexMessage;
import com.berg.fastsearch.core.car.entity.CarTemplate;
import com.berg.fastsearch.core.car.service.ICarSearchService;
import com.berg.fastsearch.core.system.search.service.impl.AbstractSearchService;
import org.springframework.stereotype.Service;

/**
 * <p></p>
 *
 * @author bo.he02@hand-china.com
 * @version v1.0
 * @apiNote Created on 18-5-7
 */
@Service
public class CarSearchServiceImpl extends AbstractSearchService<Long, CarIndexMessage, CarTemplate> implements ICarSearchService{

    @Override
    protected CarTemplate map(Long aLong) {
        return null;
    }

    @Override
    protected Class<CarIndexMessage> getIndexMessageClass() {
        return null;
    }


}

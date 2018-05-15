package com.berg.fastsearch.core.car.service.impl;

import com.berg.fastsearch.core.car.entity.CarSubscribe;
import com.berg.fastsearch.core.car.repository.CarSubscribeRepository;
import com.berg.fastsearch.core.car.service.ICarSubscribeService;
import com.berg.fastsearch.core.car.web.dto.CarSubscribeDto;
import com.berg.fastsearch.core.car.web.dto.CarSubscribeQueryCondition;
import com.berg.fastsearch.core.system.base.service.impl.AbstractBaseServiceImpl;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

/**
 * <p></p>
 *
 * @author bo.he02@hand-china.com
 * @version v1.0
 * @apiNote Created on 18-5-11
 */
@Service
public class CarSubscribeServiceImpl
        extends AbstractBaseServiceImpl<Long, CarSubscribeDto, CarSubscribe, CarSubscribeQueryCondition>
        implements ICarSubscribeService{

    /**
     * 车辆预约色repository对象
     */
    @Autowired
    private CarSubscribeRepository carSubscribeRepository;

    @Override
    protected JpaRepository<CarSubscribe, Long> getRepository() {
        return carSubscribeRepository;
    }

    @Override
    protected CarSubscribeDto createDto() {
        return new CarSubscribeDto();
    }

    @Override
    protected CarSubscribe createEntity() {
        return new CarSubscribe();
    }
}

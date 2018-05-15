package com.berg.fastsearch.core.car.service.impl;

import com.berg.fastsearch.core.car.entity.CarSeries;
import com.berg.fastsearch.core.car.repository.CarSeriesRepository;
import com.berg.fastsearch.core.car.service.ICarSeriesService;
import com.berg.fastsearch.core.car.web.dto.CarSeriesDto;
import com.berg.fastsearch.core.car.web.dto.CarSeriesQueryCondition;
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
 * @apiNote Created on 18-5-10
 */
@Service
public class CarSeriesServiceImpl
        extends AbstractBaseServiceImpl<Long, CarSeriesDto, CarSeries, CarSeriesQueryCondition>
        implements ICarSeriesService {

    @Autowired
    private CarSeriesRepository carSeriesRepository;

    @Override
    protected JpaRepository<CarSeries, Long> getRepository() {
        return carSeriesRepository;
    }

    @Override
    protected CarSeriesDto createDto() {
        return new CarSeriesDto();
    }

    @Override
    protected CarSeries createEntity() {
        return new CarSeries();
    }
}

package com.berg.fastsearch.core.car.service.impl;

import com.berg.fastsearch.core.car.entity.CarTagAss;
import com.berg.fastsearch.core.car.repository.CarTagAssRepository;
import com.berg.fastsearch.core.car.service.ICarTagAssService;
import com.berg.fastsearch.core.car.web.dto.CarTagAssDto;
import com.berg.fastsearch.core.car.web.dto.CarTagAssQueryCondition;
import com.berg.fastsearch.core.system.base.service.impl.AbstractBaseServiceImpl;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p></p>
 *
 * @author bo.he02@hand-china.com
 * @version v1.0
 * @apiNote Created on 18-5-11
 */
@Service
public class CarTagAssServiceImpl
        extends AbstractBaseServiceImpl<Long, CarTagAssDto, CarTagAss, CarTagAssQueryCondition>
        implements ICarTagAssService{

    @Autowired
    private CarTagAssRepository carTagAssRepository;

    @Override
    protected JpaRepository<CarTagAss, Long> getRepository() {
        return carTagAssRepository;
    }

    @Override
    protected CarTagAssDto createDto() {
        return new CarTagAssDto();
    }

    @Override
    protected CarTagAss createEntity() {
        return new CarTagAss();
    }

    @Override
    public List<CarTagAssDto> findByCarId(Long carId) {
        return transform2D(carTagAssRepository.findByCarId(carId));
    }
}

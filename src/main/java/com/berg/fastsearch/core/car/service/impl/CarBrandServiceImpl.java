package com.berg.fastsearch.core.car.service.impl;

import com.berg.fastsearch.core.car.entity.CarBrand;
import com.berg.fastsearch.core.car.repository.CarBrandRepository;
import com.berg.fastsearch.core.car.service.ICarBrandService;
import com.berg.fastsearch.core.car.web.dto.CarBrandDto;
import com.berg.fastsearch.core.car.web.dto.CarBrandQueryCondition;
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
public class CarBrandServiceImpl
        extends AbstractBaseServiceImpl<Long, CarBrandDto, CarBrand, CarBrandQueryCondition>
        implements ICarBrandService{

    @Autowired
    private CarBrandRepository carBrandRepository;

    @Override
    protected JpaRepository<CarBrand, Long> getRepository() {
        return carBrandRepository;
    }

    @Override
    protected CarBrandDto createDto() {
        return new CarBrandDto();
    }

    @Override
    protected CarBrand createEntity() {
        return new CarBrand();
    }
}

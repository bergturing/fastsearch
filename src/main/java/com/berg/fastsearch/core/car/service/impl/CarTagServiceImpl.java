package com.berg.fastsearch.core.car.service.impl;

import com.berg.fastsearch.core.car.entity.CarTag;
import com.berg.fastsearch.core.car.repository.CarTagRepository;
import com.berg.fastsearch.core.car.service.ICarTagService;
import com.berg.fastsearch.core.car.web.dto.CarTagDto;
import com.berg.fastsearch.core.car.web.dto.CarTagQueryCondition;
import com.berg.fastsearch.core.system.base.service.impl.AbstractBaseServiceImpl;
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
public class CarTagServiceImpl
        extends AbstractBaseServiceImpl<Long, CarTagDto, CarTag, CarTagQueryCondition>
        implements ICarTagService{

    @Autowired
    private CarTagRepository carTagRepository;

    @Override
    protected JpaRepository<CarTag, Long> getRepository() {
        return carTagRepository;
    }

    @Override
    protected CarTagDto createDto() {
        return new CarTagDto();
    }

    @Override
    protected CarTag createEntity() {
        return new CarTag();
    }
}

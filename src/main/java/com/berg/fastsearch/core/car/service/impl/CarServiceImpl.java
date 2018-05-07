package com.berg.fastsearch.core.car.service.impl;

import com.berg.fastsearch.core.car.entity.Car;
import com.berg.fastsearch.core.car.repository.CarRepository;
import com.berg.fastsearch.core.car.service.ICarService;
import com.berg.fastsearch.core.car.web.dto.CarDto;
import com.berg.fastsearch.core.system.base.service.impl.AbstractBaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p></p>
 *
 * @author bo.he02@hand-china.com
 * @version v1.0
 * @apiNote Created on 18-5-5
 */
@Service
public class CarServiceImpl extends AbstractBaseServiceImpl<CarDto, Car, Long> implements ICarService {

    @Autowired
    private CarRepository carRepository;

    @Override
    protected JpaRepository<Car, Long> getRepository() {
        return carRepository;
    }

    @Override
    protected CarDto createDto() {
        return new CarDto();
    }

    @Override
    protected Car createEntity() {
        return new Car();
    }

    @Override
    protected void create(Car entity) {
        //处理创建时间
        entity.setCreateTime(new Date());
        //处理最后更新时间
        entity.setLastUpdateTime(new Date());

        entity.setCityEnName("bj");
        entity.setRegionEnName("cpq");
        entity.setDeployeeId(1L);
        entity.setStatus("NEW");
    }

    @Override
    protected void update(Car entity) {
        //处理最后更新时间
        entity.setLastUpdateTime(new Date());
    }
}

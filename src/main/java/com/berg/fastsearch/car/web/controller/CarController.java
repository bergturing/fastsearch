package com.berg.fastsearch.car.web.controller;

import com.berg.fastsearch.car.entity.Car;
import com.berg.fastsearch.car.service.ICarService;
import com.berg.fastsearch.car.web.dto.CarDto;
import com.berg.fastsearch.car.web.dto.CarQueryCondition;
import com.berg.fastsearch.system.base.service.IBaseService;
import com.berg.fastsearch.system.base.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p></p>
 *
 * @author bo.he02@hand-china.com
 * @version v1.0
 * @apiNote Created on 18-5-5
 */
@RequestMapping(value = {"/car"})
@RestController
public class CarController extends BaseController<Long, CarDto, Car, CarQueryCondition> {

    @Autowired
    private ICarService carService;

    @Override
    protected IBaseService<CarDto, Car, Long> getService() {
        return carService;
    }
}

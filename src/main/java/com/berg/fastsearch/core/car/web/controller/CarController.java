package com.berg.fastsearch.core.car.web.controller;

import com.berg.fastsearch.core.car.service.ICarService;
import com.berg.fastsearch.core.car.web.dto.CarDto;
import com.berg.fastsearch.core.car.web.dto.CarQueryCondition;
import com.berg.fastsearch.core.system.base.service.IBaseService;
import com.berg.fastsearch.core.system.base.web.controller.BaseController;
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
public class CarController extends BaseController<Long, CarDto, CarQueryCondition> {

    @Autowired
    private ICarService carService;

    @Override
    protected IBaseService<Long, CarDto, CarQueryCondition> getService() {
        return carService;
    }
}

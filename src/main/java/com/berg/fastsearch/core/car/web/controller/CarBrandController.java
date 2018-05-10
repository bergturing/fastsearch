package com.berg.fastsearch.core.car.web.controller;

import com.berg.fastsearch.core.car.service.ICarBrandService;
import com.berg.fastsearch.core.car.web.dto.CarBrandDto;
import com.berg.fastsearch.core.car.web.dto.CarBrandQueryCondition;
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
 * @apiNote Created on 18-5-10
 */
@RequestMapping(value = {"/car/brand"})
@RestController
public class CarBrandController extends BaseController<Long, CarBrandDto, CarBrandQueryCondition> {

    @Autowired
    private ICarBrandService carBrandService;

    @Override
    protected IBaseService<Long, CarBrandDto, CarBrandQueryCondition> getService() {
        return carBrandService;
    }
}

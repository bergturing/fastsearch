package com.berg.fastsearch.core.car.web.controller;

import com.berg.fastsearch.core.car.service.brand.ICarBrandService;
import com.berg.fastsearch.core.car.web.dto.brand.CarBrandDto;
import com.berg.fastsearch.core.car.web.dto.brand.CarBrandQueryCondition;
import com.berg.fastsearch.core.system.base.service.IBaseService;
import com.berg.fastsearch.core.system.base.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p></p>
 *
 * @author bergturing@qq.com
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

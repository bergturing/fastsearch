package com.berg.fastsearch.core.car.web.controller;

import com.berg.fastsearch.core.car.service.ICarService;
import com.berg.fastsearch.core.car.web.dto.CarDto;
import com.berg.fastsearch.core.car.web.dto.CarQueryCondition;
import com.berg.fastsearch.core.system.base.service.IBaseService;
import com.berg.fastsearch.core.system.base.web.controller.BaseController;
import com.berg.fastsearch.core.system.base.web.dto.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p></p>
 *
 * @author bergturing@qq.com
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

    /**
     * 发布车辆
     * @param id
     * @return
     */
    @GetMapping("/pass/{id:\\d+}")
    public ResponseData carPass(@PathVariable("id") Long id) throws Exception {
        return ResponseData.ofSuccess(carService.pass(id));
    }

    /**
     * 车辆下架
     * @param id
     * @return
     */
    @GetMapping("/stop/{id:\\d+}")
    public ResponseData carStop(@PathVariable("id") Long id) throws Exception {
        return ResponseData.ofSuccess(carService.stop(id));
    }


}

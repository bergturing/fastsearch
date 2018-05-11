package com.berg.fastsearch.core.car.web.controller;

import com.berg.fastsearch.core.car.service.ICarSubscribeService;
import com.berg.fastsearch.core.car.web.dto.CarSubscribeDto;
import com.berg.fastsearch.core.car.web.dto.CarSubscribeQueryCondition;
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
 * @apiNote Created on 18-5-11
 */
@RequestMapping(value = {"/car/subscribe"})
@RestController
public class CarSubscribrController extends BaseController<Long, CarSubscribeDto, CarSubscribeQueryCondition> {

    @Autowired
    private ICarSubscribeService carSubscribeService;

    @Override
    protected IBaseService<Long, CarSubscribeDto, CarSubscribeQueryCondition> getService() {
        return carSubscribeService;
    }
}

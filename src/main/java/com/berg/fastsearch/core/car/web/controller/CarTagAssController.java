package com.berg.fastsearch.core.car.web.controller;

import com.berg.fastsearch.core.car.service.tag.ICarTagAssService;
import com.berg.fastsearch.core.car.web.dto.tag.CarTagAssDto;
import com.berg.fastsearch.core.car.web.dto.tag.CarTagAssQueryCondition;
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
 * @apiNote Created on 18-5-11
 */
@RequestMapping(value = {"/car/tag/ass"})
@RestController
public class CarTagAssController extends BaseController<Long, CarTagAssDto, CarTagAssQueryCondition> {

    @Autowired
    private ICarTagAssService carTagAssService;

    @Override
    protected IBaseService<Long, CarTagAssDto, CarTagAssQueryCondition> getService() {
        return carTagAssService;
    }
}

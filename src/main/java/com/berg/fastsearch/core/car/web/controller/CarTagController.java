package com.berg.fastsearch.core.car.web.controller;

import com.berg.fastsearch.core.car.service.tag.ICarTagService;
import com.berg.fastsearch.core.car.web.dto.tag.CarTagDto;
import com.berg.fastsearch.core.car.web.dto.tag.CarTagQueryCondition;
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
@RequestMapping(value = {"/car/tag"})
@RestController
public class CarTagController extends BaseController<Long, CarTagDto, CarTagQueryCondition> {

    @Autowired
    private ICarTagService carTagService;

    @Override
    protected IBaseService<Long, CarTagDto, CarTagQueryCondition> getService() {
        return carTagService;
    }
}

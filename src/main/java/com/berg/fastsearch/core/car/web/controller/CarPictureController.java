package com.berg.fastsearch.core.car.web.controller;

import com.berg.fastsearch.core.car.service.picture.ICarPictureService;
import com.berg.fastsearch.core.car.web.dto.picture.CarPictureDto;
import com.berg.fastsearch.core.car.web.dto.picture.CarPictureQueryCondition;
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
@RequestMapping(value = {"/car/picture"})
@RestController
public class CarPictureController extends BaseController<Long, CarPictureDto, CarPictureQueryCondition>{

    @Autowired
    private ICarPictureService carPictureService;


    @Override
    protected IBaseService<Long, CarPictureDto, CarPictureQueryCondition> getService() {
        return carPictureService;
    }
}

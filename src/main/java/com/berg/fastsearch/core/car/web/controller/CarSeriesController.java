package com.berg.fastsearch.core.car.web.controller;

import com.berg.fastsearch.core.car.service.series.ICarSeriesService;
import com.berg.fastsearch.core.car.web.dto.series.CarSeriesDto;
import com.berg.fastsearch.core.car.web.dto.series.CarSeriesQueryCondition;
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
@RequestMapping(value = {"/car/series"})
@RestController
public class CarSeriesController extends BaseController<Long, CarSeriesDto, CarSeriesQueryCondition> {

    @Autowired
    private ICarSeriesService carSeriesService;

    @Override
    protected IBaseService<Long, CarSeriesDto, CarSeriesQueryCondition> getService() {
        return carSeriesService;
    }
}

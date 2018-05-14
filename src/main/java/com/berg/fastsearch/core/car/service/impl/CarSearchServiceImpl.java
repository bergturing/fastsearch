package com.berg.fastsearch.core.car.service.impl;

import com.berg.fastsearch.core.car.entity.CarIndexMessage;
import com.berg.fastsearch.core.car.entity.CarTemplate;
import com.berg.fastsearch.core.car.service.ICarSearchService;
import com.berg.fastsearch.core.car.service.ICarSeriesService;
import com.berg.fastsearch.core.car.service.ICarService;
import com.berg.fastsearch.core.car.web.dto.CarSeriesDto;
import com.berg.fastsearch.core.system.search.service.impl.AbstractSearchService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p></p>
 *
 * @author bo.he02@hand-china.com
 * @version v1.0
 * @apiNote Created on 18-5-7
 */
@Service
public class CarSearchServiceImpl extends AbstractSearchService<Long, CarIndexMessage, CarTemplate> implements ICarSearchService{

    @Autowired
    private ICarService carService;

    @Override
    protected CarTemplate map(Long id) {
        CarTemplate carTemplate = new CarTemplate();


        //设置车辆的基本信息
        BeanUtils.copyProperties(carService.findOne(id), carTemplate);;





        return carTemplate;
    }

    @Override
    protected Class<CarIndexMessage> getIndexMessageClass() {
        return null;
    }


}

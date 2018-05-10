package com.berg.fastsearch.core.car.service;

import com.berg.fastsearch.core.car.entity.Car;
import com.berg.fastsearch.core.car.web.dto.CarDto;
import com.berg.fastsearch.core.car.web.dto.CarQueryCondition;
import com.berg.fastsearch.core.system.base.service.IBaseService;

import java.util.List;

/**
 * <p></p>
 *
 * @author bo.he02@hand-china.com
 * @version v1.0
 * @apiNote Created on 18-5-5
 */
public interface ICarService extends IBaseService<Long, CarDto, CarQueryCondition> {

//    /**
//     * 全地图查询
//     * @param carQueryCondition
//     * @return
//     */
//    List<CarDto> wholeMapQuery(CarQueryCondition carQueryCondition);
}

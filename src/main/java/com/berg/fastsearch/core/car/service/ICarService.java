package com.berg.fastsearch.core.car.service;

import com.berg.fastsearch.core.car.web.dto.CarDto;
import com.berg.fastsearch.core.car.web.dto.CarQueryCondition;
import com.berg.fastsearch.core.system.base.service.IBaseService;

import java.util.List;

/**
 * <p></p>
 *
 * @author bergturing@qq.com
 * @version v1.0
 * @apiNote Created on 18-5-5
 */
public interface ICarService extends IBaseService<Long, CarDto, CarQueryCondition> {

    /**
     * 查看车辆
     * @param id            主键
     * @return              处理的结果
     * @throws Exception    处理异常
     */
    CarDto watchCar(Long id) throws Exception;

    /**
     * 车辆发布
     * @param id        主键
     * @return          处理的结果
     */
    CarDto pass(Long id) throws Exception;

    /**
     * 车辆下架
     * @param id        主键
     * @return          处理结果
     */
    CarDto stop(Long id) throws Exception;
}

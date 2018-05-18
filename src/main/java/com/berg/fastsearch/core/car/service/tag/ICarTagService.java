package com.berg.fastsearch.core.car.service.tag;

import com.berg.fastsearch.core.car.web.dto.tag.CarTagDto;
import com.berg.fastsearch.core.car.web.dto.tag.CarTagQueryCondition;
import com.berg.fastsearch.core.system.base.service.IBaseService;

import java.util.List;

/**
 * <p></p>
 *
 * @author bo.he02@hand-china.com
 * @version v1.0
 * @apiNote Created on 18-5-11
 */
public interface ICarTagService extends IBaseService<Long, CarTagDto, CarTagQueryCondition> {

    /**
     * 根据车辆的主键找到车辆的标签
     * @param carId     车辆的主键
     * @return          找到的车辆的标签
     */
    List<CarTagDto> findByCarId(Long carId);
}

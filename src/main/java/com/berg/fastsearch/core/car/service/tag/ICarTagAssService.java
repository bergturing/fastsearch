package com.berg.fastsearch.core.car.service.tag;

import com.berg.fastsearch.core.car.web.dto.tag.CarTagAssDto;
import com.berg.fastsearch.core.car.web.dto.tag.CarTagAssQueryCondition;
import com.berg.fastsearch.core.system.base.service.IBaseService;

import java.util.List;

/**
 * <p></p>
 *
 * @author bo.he02@hand-china.com
 * @version v1.0
 * @apiNote Created on 18-5-11
 */
public interface ICarTagAssService extends IBaseService<Long, CarTagAssDto, CarTagAssQueryCondition> {

    /**
     * 通过carId寻找carTagAssDto对象
     * @param carId     车辆主键
     * @return          找到的carTagAssDto对象
     */
    List<CarTagAssDto> findByCarId(Long carId);
}

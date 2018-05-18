package com.berg.fastsearch.core.car.service.picture;

import com.berg.fastsearch.core.car.web.dto.picture.CarPictureDto;
import com.berg.fastsearch.core.car.web.dto.picture.CarPictureQueryCondition;
import com.berg.fastsearch.core.system.base.service.IBaseService;

import java.util.List;

/**
 * <p></p>
 *
 * @author bo.he02@hand-china.com
 * @version v1.0
 * @apiNote Created on 18-5-10
 */
public interface ICarPictureService extends IBaseService<Long, CarPictureDto, CarPictureQueryCondition> {

    /**
     * 通过车辆的主键找到该车辆的所有图片
     * @param carId     车辆的主键
     * @return          该车辆的图片
     */
    List<CarPictureDto> findByCarId(Long carId);
}

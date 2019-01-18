package com.berg.fastsearch.core.car.repository;

import com.berg.fastsearch.core.car.entity.CarPicture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * <p></p>
 *
 * @author bergturing@qq.com
 * @version v1.0
 * @apiNote Created on 18-5-10
 */
public interface CarPictureRepository extends JpaRepository<CarPicture, Long> {

    /**
     * 通过车辆的主键找到该车辆的所有图片
     * @param carId     车辆的主键
     * @return          该车辆的图片
     */
    List<CarPicture> findByCarId(Long carId);
}

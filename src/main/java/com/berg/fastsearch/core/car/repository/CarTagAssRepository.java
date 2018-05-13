package com.berg.fastsearch.core.car.repository;

import com.berg.fastsearch.core.car.entity.CarTagAss;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * <p></p>
 *
 * @author bo.he02@hand-china.com
 * @version v1.0
 * @apiNote Created on 18-5-11
 */
public interface CarTagAssRepository extends JpaRepository<CarTagAss, Long> {

    /**
     * 通过carId寻找carTagAss对象
     * @param carId     车辆主键
     * @return          找到的carTagAss对象
     */
    List<CarTagAss> findByCarId(Long carId);
}

package com.berg.fastsearch.core.car.repository;

import com.berg.fastsearch.core.car.entity.CarSeries;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * <p></p>
 *
 * @author bergturing@qq.com
 * @version v1.0
 * @apiNote Created on 18-5-10
 */
public interface CarSeriesRepository extends JpaRepository<CarSeries, Long> {
}

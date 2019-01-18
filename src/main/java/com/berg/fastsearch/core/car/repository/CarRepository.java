package com.berg.fastsearch.core.car.repository;

import com.berg.fastsearch.core.car.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * <p></p>
 *
 * @author bergturing@qq.com
 * @version v1.0
 * @apiNote Created on 18-5-5
 */
public interface CarRepository extends JpaRepository<Car, Long> {
}

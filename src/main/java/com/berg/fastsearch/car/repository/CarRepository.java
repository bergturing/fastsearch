package com.berg.fastsearch.car.repository;

import com.berg.fastsearch.car.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * <p></p>
 *
 * @author bo.he02@hand-china.com
 * @version v1.0
 * @apiNote Created on 18-5-5
 */
public interface CarRepository extends JpaRepository<Car, Long> {
}

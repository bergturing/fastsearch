package com.berg.fastsearch.core.car.repository;

import com.berg.fastsearch.core.car.entity.CarSubscribe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * <p></p>
 *
 * @author bo.he02@hand-china.com
 * @version v1.0
 * @apiNote Created on 18-5-11
 */
public interface CarSubscribeRepository extends JpaRepository<CarSubscribe, Long>{

    /**
     *
     * @param carId
     * @param userId
     * @return
     */
    CarSubscribe findByCarIdAndUserId(Long carId, Long userId);

    /**
     *
     * @param userId
     * @param status
     * @return
     */
    List<CarSubscribe> findByUserIdAndStatus(Long userId, String status);
}

package com.berg.fastsearch.core.car.web.dto.subscribe;

import com.berg.fastsearch.core.system.base.web.dto.BaseQueryCondition;

/**
 * <p></p>
 *
 * @author bo.he02@hand-china.com
 * @version v1.0
 * @apiNote Created on 18-5-11
 */
public class CarSubscribeQueryCondition extends BaseQueryCondition {
    private static final long serialVersionUID = 8021973393144129698L;

    private Long userId;

    private Long carId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }
}

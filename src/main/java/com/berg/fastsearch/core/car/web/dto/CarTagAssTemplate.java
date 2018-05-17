package com.berg.fastsearch.core.car.web.dto;

import com.berg.fastsearch.core.system.search.template.BaseTemplate;

/**
 * <p></p>
 *
 * @author bo.he02@hand-china.com
 * @version v1.0
 * @apiNote Created on 18-5-17
 */
public class CarTagAssTemplate extends BaseTemplate<Long> {
    private static final long serialVersionUID = -4278270908882357499L;

    private Long carId;

    private Long carTagId;

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public Long getCarTagId() {
        return carTagId;
    }

    public void setCarTagId(Long carTagId) {
        this.carTagId = carTagId;
    }
}

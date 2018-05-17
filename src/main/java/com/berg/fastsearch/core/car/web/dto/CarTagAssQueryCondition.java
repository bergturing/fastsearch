package com.berg.fastsearch.core.car.web.dto;

import com.berg.fastsearch.core.system.base.web.dto.BaseQueryCondition;

/**
 * <p></p>
 *
 * @author bo.he02@hand-china.com
 * @version v1.0
 * @apiNote Created on 18-5-11
 */
public class CarTagAssQueryCondition extends BaseQueryCondition{
    private static final long serialVersionUID = 7477825265166014485L;

    public static final String FIELD_ID = "id";

    public static final String FIELD_CAR_ID = "carId";

    public static final String FIELD_CAR_TAG_ID = "carTagId";

    private Long id;

    private Long carId;

    private Long carTagId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

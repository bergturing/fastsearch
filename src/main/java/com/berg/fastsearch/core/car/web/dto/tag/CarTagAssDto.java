package com.berg.fastsearch.core.car.web.dto.tag;

import com.berg.fastsearch.core.system.base.web.dto.BaseDto;

/**
 * <p></p>
 *
 * @author bo.he02@hand-china.com
 * @version v1.0
 * @apiNote Created on 18-5-11
 */
public class CarTagAssDto extends BaseDto<Long> {
    private static final long serialVersionUID = 8799822934068040520L;

    private Long carId;

    private Long carTagId;

    private String carTitle;

    private String carTagName;

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

    public String getCarTitle() {
        return carTitle;
    }

    public void setCarTitle(String carTitle) {
        this.carTitle = carTitle;
    }

    public String getCarTagName() {
        return carTagName;
    }

    public void setCarTagName(String carTagName) {
        this.carTagName = carTagName;
    }
}

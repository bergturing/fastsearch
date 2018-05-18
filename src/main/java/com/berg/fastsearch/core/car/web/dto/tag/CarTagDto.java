package com.berg.fastsearch.core.car.web.dto.tag;

import com.berg.fastsearch.core.system.base.web.dto.BaseDto;

/**
 * <p></p>
 *
 * @author bo.he02@hand-china.com
 * @version v1.0
 * @apiNote Created on 18-5-11
 */
public class CarTagDto extends BaseDto<Long> {

    private static final long serialVersionUID = -458183218707224746L;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

package com.berg.fastsearch.core.car.web.dto;

import com.berg.fastsearch.core.system.base.web.dto.BaseDto;

/**
 * <p></p>
 *
 * @author bo.he02@hand-china.com
 * @version v1.0
 * @apiNote Created on 18-5-10
 */
public class CarBrandDto extends BaseDto<Long> {
    private static final long serialVersionUID = -1221539576183836216L;

    private String code;

    private String name;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

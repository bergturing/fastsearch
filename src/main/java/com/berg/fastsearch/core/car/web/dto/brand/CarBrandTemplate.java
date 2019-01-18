package com.berg.fastsearch.core.car.web.dto.brand;

import com.berg.fastsearch.core.system.search.template.BaseTemplate;

/**
 * <p></p>
 *
 * @author bergturing@qq.com
 * @version v1.0
 * @apiNote Created on 18-5-16
 */
public class CarBrandTemplate extends BaseTemplate<Long> {
    private static final long serialVersionUID = -2104683126989191363L;

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

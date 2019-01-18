package com.berg.fastsearch.core.car.web.dto.series;

import com.berg.fastsearch.core.system.search.template.BaseTemplate;

/**
 * <p></p>
 *
 * @author bergturing@qq.com
 * @version v1.0
 * @apiNote Created on 18-5-16
 */
public class CarSeriesTemplate extends BaseTemplate<Long> {
    private static final long serialVersionUID = 5703532335952045004L;

    private Long brandId;

    private String code;

    private String name;

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

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

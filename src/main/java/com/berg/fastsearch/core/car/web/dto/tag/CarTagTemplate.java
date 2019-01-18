package com.berg.fastsearch.core.car.web.dto.tag;

import com.berg.fastsearch.core.system.search.template.BaseTemplate;

/**
 * <p></p>
 *
 * @author bergturing@qq.com
 * @version v1.0
 * @apiNote Created on 18-5-17
 */
public class CarTagTemplate extends BaseTemplate<Long> {
    private static final long serialVersionUID = 6509136647598065814L;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

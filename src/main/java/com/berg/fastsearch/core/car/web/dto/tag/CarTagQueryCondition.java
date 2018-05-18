package com.berg.fastsearch.core.car.web.dto.tag;

import com.berg.fastsearch.core.system.base.web.dto.BaseQueryCondition;

/**
 * <p></p>
 *
 * @author bo.he02@hand-china.com
 * @version v1.0
 * @apiNote Created on 18-5-11
 */
public class CarTagQueryCondition extends BaseQueryCondition {
    private static final long serialVersionUID = -8055863339044704041L;

    public static final String FIELD_ID = "id";

    public static final String FIELD_NAME = "name";

    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

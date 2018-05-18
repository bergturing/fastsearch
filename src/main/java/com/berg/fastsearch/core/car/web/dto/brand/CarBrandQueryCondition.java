package com.berg.fastsearch.core.car.web.dto.brand;

import com.berg.fastsearch.core.system.base.web.dto.BaseQueryCondition;

/**
 * <p></p>
 *
 * @author bo.he02@hand-china.com
 * @version v1.0
 * @apiNote Created on 18-5-10
 */
public class CarBrandQueryCondition extends BaseQueryCondition {
    private static final long serialVersionUID = 5477447144439298517L;

    public static final String FIELD_ID = "id";

    public static final String FIELD_CODE = "code";

    public static final String FIELD_NAME = "name";

    private Long id;

    private String code;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

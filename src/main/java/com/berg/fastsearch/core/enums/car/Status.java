package com.berg.fastsearch.core.enums.car;

/**
 * <p>车辆的状态</p>
 *
 * @author bo.he02@hand-china.com
 * @version v1.0
 * @apiNote Created on 18-5-17
 */
public enum  Status {
    NEW("NEW", "新建");

    private String code;

    private String name;

    Status(String code, String name) {
        this.code = code;
        this.name = name;
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

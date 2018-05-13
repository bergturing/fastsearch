package com.berg.fastsearch.core.enums.car;

/**
 * <p>车辆的状态</p>
 *
 * @version: v1.0
 * @author: bo.he02@hand-china.com
 * @apiNote: Created on 18-5-11
 */
public enum CarStatus {
    ADDED("ADDED", "已加入待看"),
    SUBSCRIBED("SUBSCRIBED", "已预约时间"),
    SAW("SAW", "已看过此车");

    private String code;

    private String name;

    CarStatus(String code, String name) {
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

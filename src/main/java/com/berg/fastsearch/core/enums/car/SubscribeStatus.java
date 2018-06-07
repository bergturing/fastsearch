package com.berg.fastsearch.core.enums.car;

/**
 * <p></p>
 *
 * @author bo.he02@hand-china.com
 * @version v1.0
 * @apiNote Created on 18-5-17
 */
public enum SubscribeStatus {
    ADDED("ADDED", "已收藏"),
    SUBSCRIBED("SUBSCRIBED", "已预约时间"),
    SAW("SAW", "已看过此车");

    private String code;

    private String name;

    SubscribeStatus(String code, String name) {
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

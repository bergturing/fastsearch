package com.berg.fastsearch.core.enums.address;

import com.berg.fastsearch.core.address.entity.SupportAddress;

/**
 * <p>行政级别定义</p>
 *
 * @version: v1.0
 * @author: bo.he02@hand-china.com
 * @apiNote: Created on 18-5-13
 */
public enum Level {
    CITY("city", "城市"),
    REGION("region", "区域");

    private String code;

    private String name;

    Level(String code, String name) {
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

    public static Level of(String code) {
        for (Level level : Level.values()) {
            if (level.getCode().equals(code)) {
                return level;
            }
        }

        throw new IllegalArgumentException();
    }
}

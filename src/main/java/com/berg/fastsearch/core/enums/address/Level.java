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
    CITY("city"),
    REGION("region");

    private String value;

    Level(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Level of(String value) {
        for (Level level : Level.values()) {
            if (level.getValue().equals(value)) {
                return level;
            }
        }

        throw new IllegalArgumentException();
    }
}

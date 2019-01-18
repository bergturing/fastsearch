package com.berg.fastsearch.core.enums.account;

/**
 * <p></p>
 *
 * @version: v1.0
 * @author: bergturing@qq.com
 * @apiNote: Created on 18-5-4
 */
public enum UserStatus {
    NORMAL("NORMAL", "正常"),
    BAN("BAN", "已禁用");

    /**
     * 值
     */
    private String value;

    /**
     * 含义
     */
    private String meaning;

    UserStatus(String value, String meaning) {
        this.value = value;
        this.meaning = meaning;
    }

    public String getValue() {
        return value;
    }

    public String getMeaning() {
        return meaning;
    }
}

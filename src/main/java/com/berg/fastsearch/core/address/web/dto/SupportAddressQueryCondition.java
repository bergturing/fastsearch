package com.berg.fastsearch.core.address.web.dto;

import com.berg.fastsearch.core.system.base.web.dto.BaseQueryCondition;

/**
 * <p></p>
 *
 * @author bergturing@qq.com
 * @version v1.0
 * @apiNote Created on 18-5-10
 */
public class SupportAddressQueryCondition extends BaseQueryCondition {
    private static final long serialVersionUID = -322729732789078291L;

    /**
     * 行政级别字段名
     */
    public static final String FIELD_LEVEL = "level";

    /**
     * 地点所属对象字段名
     */
    public static final String FIELD_BELONG_TO = "belongTo";

    /**
     * 地点的行政级别
     */
    private String level;

    /**
     * 地点的所属对象的id
     */
    private Long belongTo;

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Long getBelongTo() {
        return belongTo;
    }

    public void setBelongTo(Long belongTo) {
        this.belongTo = belongTo;
    }
}
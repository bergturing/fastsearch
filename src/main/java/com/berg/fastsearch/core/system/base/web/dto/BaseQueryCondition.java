package com.berg.fastsearch.core.system.base.web.dto;

import java.io.Serializable;

/**
 * <p>基本查询对象</p>
 *
 * @author bergturing@qq.com
 * @version v1.0
 * @apiNote Created on 18-5-4
 */
public abstract class BaseQueryCondition implements Serializable{
    private static final long serialVersionUID = -4319017782871574333L;

    /**
     * 默认页码
     */
    public static final Integer DEFAULT_START = 0;

    /**
     * 默认页码大小
     */
    public static final Integer DEFAULT_SIZE = 20;

    /**
     * 排序顺序
     */
    public static final String ORDER_ASC = "ASC";

    /**
     * 排序反序
     */
    public static final String ORDER_DESC = "DESC";

    /**
     * 分页的页码
     */
    private Integer start = DEFAULT_START;

    /**
     * 分页大小
     */
    private Integer size = DEFAULT_SIZE;

    /**
     * 排序的字段
     */
    private String orderBy;

    /**
     * 排序的方向 ASC DESC
     */
    private String orderDirection;


    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getOrderDirection() {
        return orderDirection;
    }

    public void setOrderDirection(String orderDirection) {
        this.orderDirection = orderDirection;
    }
}

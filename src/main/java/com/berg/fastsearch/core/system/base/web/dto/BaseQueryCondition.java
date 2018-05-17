package com.berg.fastsearch.core.system.base.web.dto;

import java.io.Serializable;

/**
 * <p>基本查询对象</p>
 *
 * @author bo.he02@hand-china.com
 * @version v1.0
 * @apiNote Created on 18-5-4
 */
public abstract class BaseQueryCondition implements Serializable{
    private static final long serialVersionUID = -4319017782871574333L;

    /**
     * 默认页码
     */
    public static final Integer DEFAULT_PAGE = 1;

    /**
     * 默认页码大小
     */
    public static final Integer DEFAULT_PAGE_SIZE = 20;

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
    private Integer page = DEFAULT_PAGE;

    /**
     * 分页大小
     */
    private Integer pageSize = DEFAULT_PAGE_SIZE;

    /**
     * 排序的字段
     */
    private String orderBy;

    /**
     * 排序的方向 ASC DESC
     */
    private String orderDirection;


    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
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

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
     * 分页的页码
     */
    private Integer page;

    /**
     * 分页大小
     */
    private Integer pageSize;

    /**
     * 排序的方向 ASC DESC
     */
    private String direction;

    /**
     * 排序的字段
     */
    private String orderBy;


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

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }
}

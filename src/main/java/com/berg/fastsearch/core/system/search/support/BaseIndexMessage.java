package com.berg.fastsearch.core.system.search.support;

import java.io.Serializable;

/**
 * <p></p>
 *
 * @author bo.he02@hand-china.com
 * @version v1.0
 * @apiNote Created on 18-5-7
 */
public abstract class BaseIndexMessage<ID extends Serializable> implements Serializable{

    public static final String INDEX = "index";
    public static final String REMOVE = "remove";

    public static final Integer MAX_RETRY = 3;
    private static final long serialVersionUID = -4886731026065822810L;

    private ID id;
    private String operation;
    private Integer retry = 0;

    public void init(ID id, String operation, Integer retry){
        this.id = id;
        this.operation = operation;
        this.retry = retry;
    }

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Integer getRetry() {
        return retry;
    }

    public void setRetry(Integer retry) {
        this.retry = retry;
    }
}

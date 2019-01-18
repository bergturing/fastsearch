package com.berg.fastsearch.core.system.search.template;

import java.io.Serializable;

/**
 * <p></p>
 *
 * @author bergturing@qq.com
 * @version v1.0
 * @apiNote Created on 18-5-7
 */
public abstract class BaseTemplate<ID extends Serializable> implements Serializable {
    private static final long serialVersionUID = 8826219161964955776L;

    public static final String FIELD_ID = "id";

    private ID id;

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }
}

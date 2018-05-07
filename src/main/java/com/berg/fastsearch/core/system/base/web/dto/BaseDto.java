package com.berg.fastsearch.core.system.base.web.dto;

import java.io.Serializable;

/**
 * <p></p>
 *
 * @author bo.he02@hand-china.com
 * @version v1.0
 * @apiNote Created on 18-5-3
 */
public abstract class BaseDto<ID extends Serializable> implements Serializable {
    private static final long serialVersionUID = 1029195500398790756L;

    /**
     * 角色的主键
     */
    private ID id;

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }
}

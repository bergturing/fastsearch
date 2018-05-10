package com.berg.fastsearch.core.system.search.service;

import java.io.Serializable;

/**
 * <p></p>
 *
 * @author bo.he02@hand-china.com
 * @version v1.0
 * @apiNote Created on 18-5-7
 */
public interface ISearchService<ID extends Serializable> {
    /**
     * 索引目标
     * @param id    目标主键
     */
    void index(ID id);

    /**
     * 移除索引
     * @param id    目标主键
     */
    void remove(ID id);
}

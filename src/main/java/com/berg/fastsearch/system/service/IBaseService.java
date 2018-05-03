package com.berg.fastsearch.system.service;

import java.util.List;

/**
 * <p></p>
 *
 * @author bo.he02@hand-china.com
 * @version v1.0
 * @apiNote Created on 18-5-3
 */
public interface IBaseService<DTO, ENTITY> {
    /**
     *
     * @return
     */
    List<DTO> findAll();
}

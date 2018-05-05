package com.berg.fastsearch.system.base.service;

import java.util.List;

/**
 * <p></p>
 *
 * @author bo.he02@hand-china.com
 * @version v1.0
 * @apiNote Created on 18-5-3
 */
public interface IBaseService<DTO, ENTITY, ID> {
    /**
     * 查询所有的数据
     * @return  查询到的所有的数据
     */
    List<DTO> findAll();

    /**
     * 根据主键查询数据
     * @param id        主键
     * @return          查询结果
     */
    DTO findOne(ID id);

    /**
     * 新增一条的信息
     * @param dto       dto对象
     * @return          保存的结果
     */
    DTO create(DTO dto);

    /**
     * 更新一条的信息
     * @param dto       dto对象
     * @return          保存的结果
     */
    DTO update(DTO dto);

    /**
     * 删除一条数据,并返回删除的对象
     * @param id        删除的对象的主键
     * @return          删除的对象
     */
    DTO delete(ID id);
}

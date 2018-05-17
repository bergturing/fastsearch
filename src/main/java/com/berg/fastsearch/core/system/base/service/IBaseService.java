package com.berg.fastsearch.core.system.base.service;

import com.berg.fastsearch.core.system.base.web.dto.BaseDto;
import com.berg.fastsearch.core.system.base.web.dto.BaseQueryCondition;

import java.io.Serializable;
import java.util.List;

/**
 * <p></p>
 *
 * @author bo.he02@hand-china.com
 * @version v1.0
 * @apiNote Created on 18-5-3
 */
public interface IBaseService<
        ID extends Serializable,
        DTO extends BaseDto<ID>,
        CONDITION extends BaseQueryCondition> {

    /**
     * 查询所有的数据
     * @return  查询到的所有的数据
     */
    List<DTO> findAll(CONDITION condition);

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
    DTO create(DTO dto) throws Exception;

    /**
     * 更新一条的信息
     * @param dto       dto对象
     * @return          保存的结果
     */
    DTO update(DTO dto) throws Exception;

    /**
     * 删除一条数据,并返回删除的对象
     * @param id        删除的对象的主键
     * @return          删除的对象
     */
    DTO delete(ID id);

    /**
     * 批量删除
     * @param ids   主键列表
     * @return      删除的对象
     */
    List<DTO> batchDelete(List<ID> ids);

    /**
     * 索引所有的信息
     */
    boolean indexAll();
}

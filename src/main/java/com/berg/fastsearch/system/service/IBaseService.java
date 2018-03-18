package com.berg.fastsearch.system.service;

import java.util.List;

/**
 * <p>基础服务接口</p>
 *
 * @author bo.he02@hand-china.com
 * @version v1.0
 * @apiNote Created on 18-3-18
 */
public interface IBaseService<T> {
    /**
     * 条件查询
     * @param condition 查询条件
     * @return          查询结果
     */
    List<T> select(T condition);

    /**
     * 带分页的条件查询
     * @param condition     查询条件
     * @param page          页码
     * @param pageSize      分页大小
     * @return              查询结果
     */
    List<T> select(T condition, int page, int pageSize);

    /**
     * 查询所有数据
     * @return      查询结果
     */
    List<T> selectAll();

    /**
     * 通过主键查询
     * @param condition      查询的对象
     * @return              查询结果
     */
    T selectByPrimaryKey(T condition);

    /**
     * 插入数据
     * @param record    插入的对象
     * @return          插入的结果
     */
    int insert(T record);

    /**
     * 选择性的插入
     * @param record    插入的对象
     * @return          插入的结果
     */
    int insertSelective(T record);

    /**
     * 通过主键更新数据
     * @param record    更新的数据对象
     * @return          更新的结果
     */
    T updateByPrimaryKey(T record);

    /**
     * 通过主键选择性的更新数据
     * @param record    更新的数据对象
     * @return          更新的结果
     */
    T updateByPrimaryKeySelective(T record);

    /**
     * 批量更新
     * @param records   需更新的数据对象
     * @return          更新的结果对象
     */
    List<T> batchUpdate(List<T> records);

    /**
     * 通过主键删除数据
     * @param record        要删除的数据对象
     * @return              删除的结果
     */
    int deleteByPrimaryKey(T record);

    /**
     * 批量删除
     * @param records   需要删除的对象
     * @return          删除的结果
     */
    int batchDelete(List<T> records);
}

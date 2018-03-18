package com.berg.fastsearch.system.service.impl;

import com.berg.fastsearch.system.mapper.BaseMapper;
import com.berg.fastsearch.system.service.IBaseService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>基础服务的实现类</p>
 *
 * @author bo.he02@hand-china.com
 * @version v1.0
 * @apiNote Created on 18-3-18
 */
public abstract class BaseServiceImpl<T> implements IBaseService<T>{

    @Autowired
    private BaseMapper<T> mapper;

    @Override
    public List<T> select(T condition) {
        return mapper.select(condition);
    }

    @Override
    public List<T> select(T condition, int page, int pageSize) {
        PageHelper.startPage(page, pageSize);
        return this.select(condition);
    }

    @Override
    public List<T> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public T selectByPrimaryKey(T condition) {
        return mapper.selectByPrimaryKey(condition);
    }

    @Override
    public int insert(T record) {
        return mapper.insert(record);
    }

    @Override
    public int insertSelective(T record) {
        return mapper.insertSelective(record);
    }

    @Override
    public T updateByPrimaryKey(T record) {
        return mapper.updateByPrimaryKey(record);
    }

    @Override
    public T updateByPrimaryKeySelective(T record) {
        return mapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public List<T> batchUpdate(List<T> records) {
        List<T> results = new ArrayList<>();

        if(!CollectionUtils.isEmpty(records)){
            records.forEach(record -> {
                results.add(updateByPrimaryKey(record));
            });
        }

        return results;
    }

    @Override
    public int deleteByPrimaryKey(T record) {
        return mapper.deleteByPrimaryKey(record);
    }

    @Override
    public int batchDelete(List<T> records) {
        Integer count = 0;

        if(!CollectionUtils.isEmpty(records)){
            for (T record : records) {
                count += deleteByPrimaryKey(record);
            }
        }

        return count;
    }
}

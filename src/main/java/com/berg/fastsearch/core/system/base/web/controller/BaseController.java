package com.berg.fastsearch.core.system.base.web.controller;

import com.berg.fastsearch.core.system.base.entity.BaseEntity;
import com.berg.fastsearch.core.system.base.service.IBaseService;
import com.berg.fastsearch.core.system.base.web.dto.BaseDto;
import com.berg.fastsearch.core.system.base.web.dto.BaseQueryCondition;
import com.berg.fastsearch.core.system.base.web.dto.ResponseData;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.Serializable;

/**
 * <p></p>
 *
 * @author bo.he02@hand-china.com
 * @version v1.0
 * @apiNote Created on 18-5-5
 */
public abstract class BaseController<
        ID extends Serializable,
        DTO extends BaseDto<ID>,
        ENTITY extends BaseEntity,
        CONDITION extends BaseQueryCondition>{

    /**
     * 查询用户信息
     * @return      查询的结果
     */
    @GetMapping
    public ResponseData query(CONDITION condition){
        return ResponseData.ofSuccess(getService().findAll());
    }

    /**
     * 获取用户详细信息
     * @return      用户的详细信息
     */
    @GetMapping("/{id:\\d+}")
    public ResponseData getInfo(@PathVariable("id") ID id){
        return ResponseData.ofSuccess(getService().findOne(id));
    }

    /**
     * 创建用户对象
     * @param roleDto   角色的详细信息
     * @return          新增之后的数据
     */
    @PostMapping
    public ResponseData create(@Valid @RequestBody DTO roleDto){
        return ResponseData.ofSuccess(getService().create(roleDto));
    }

    /**
     * 根据id与用户对象修改用户信息
     * @param id            用户的额主键
     * @param roleDto       提交的角色的数据
     * @return              处理的结果
     */
    @PutMapping("/{id:\\d+}")
    public ResponseData update(@PathVariable("id") ID id,
                               @Valid @RequestBody DTO roleDto){
        roleDto.setId(id);
        return ResponseData.ofSuccess(getService().update(roleDto));
    }

    @DeleteMapping("/{id:\\d+}")
    public ResponseData delete(@PathVariable("id") ID id){
        return ResponseData.ofSuccess(getService().delete(id));
    }

    /**
     * 获取服务对象
     * @return      返回服务对象
     */
    protected abstract IBaseService<DTO, ENTITY, ID> getService();
}

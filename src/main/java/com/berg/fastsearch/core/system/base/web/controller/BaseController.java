package com.berg.fastsearch.core.system.base.web.controller;

import com.berg.fastsearch.core.system.base.entity.BaseEntity;
import com.berg.fastsearch.core.system.base.service.IBaseService;
import com.berg.fastsearch.core.system.base.web.dto.BaseDto;
import com.berg.fastsearch.core.system.base.web.dto.BaseQueryCondition;
import com.berg.fastsearch.core.system.base.web.dto.ResponseData;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.List;

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
        CONDITION extends BaseQueryCondition>{

    /**
     * 查询用户信息
     * @return      查询的结果
     */
    @GetMapping
    public ResponseData query(CONDITION condition){
        return ResponseData.ofSuccess(getService().findAll(condition).getResult());
    }

    /**
     * 获取详细信息
     * @return      用   详细信息
     */
    @GetMapping("/{id:\\d+}")
    public ResponseData getInfo(@PathVariable("id") ID id){
        return ResponseData.ofSuccess(getService().findOne(id));
    }

    /**
     * 创建对象
     * @param dto       详细信息
     * @return          新增之后的数据
     */
    @PostMapping("/form")
    public ResponseData formCreate(@Valid @ModelAttribute("form-add") DTO dto) throws Exception {
        if(dto.getId()!=null){
            return ResponseData.ofSuccess(getService().update(dto));
        }else{
            return ResponseData.ofSuccess(getService().create(dto));
        }
    }

    /**
     * 创建对象
     * @param dto       详细信息
     * @return          新增之后的数据
     */
    @PostMapping
    public ResponseData bodyCreate(@Valid @RequestBody DTO dto) throws Exception {
        return ResponseData.ofSuccess(getService().create(dto));
    }

    /**
     * 根据id与对象修改信息
     * @param id            主键
     * @param dto           提交的数据
     * @return              处理的结果
     */
    @PutMapping("/{id:\\d+}")
    public ResponseData update(@PathVariable("id") ID id,
                               @Valid @RequestBody DTO dto) throws Exception {
        dto.setId(id);
        return ResponseData.ofSuccess(getService().update(dto));
    }

    @DeleteMapping("/{id:\\d+}")
    public ResponseData delete(@PathVariable("id") ID id){
        return ResponseData.ofSuccess(getService().delete(id));
    }

    @PostMapping("/batch")
    public ResponseData batchdelete(@RequestBody List<ID> ids){
        return ResponseData.ofSuccess(getService().batchDelete(ids));
    }


    @GetMapping("/indexAll")
    public ResponseData indexAll(){
        return ResponseData.ofSuccess(getService().indexAll());
    }

    /**
     * 获取服务对象
     * @return      返回服务对象
     */
    protected abstract IBaseService<ID, DTO, CONDITION> getService();
}

package com.berg.fastsearch.account.web.controllers;

import com.berg.fastsearch.account.service.IUserService;
import com.berg.fastsearch.account.web.dto.UserDto;
import com.berg.fastsearch.account.web.dto.UserQueryCondition;
import com.berg.fastsearch.system.base.web.dto.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p></p>
 *
 * @author bo.he02@hand-china.com
 * @version v1.0
 * @apiNote Created on 18-3-18
 */
@RequestMapping(value = {"/account/user"})
@RestController
public class UserController {

    /**
     * 用户的服务
     */
    @Autowired
    private IUserService userService;

    /**
     * 查询用户信息
     * @return      查询的结果
     */
    @GetMapping
    public ResponseData query(UserQueryCondition condition){
        return ResponseData.ofSuccess(userService.findAll());
    }

    /**
     * 获取用户详细信息
     * @return      用户的详细信息
     */
    @GetMapping("/{id:\\d+}")
    public ResponseData getInfo(@PathVariable("id") Long id){
        return ResponseData.ofSuccess(userService.findOne(id));
    }

    /**
     * 创建用户对象
     * @param userDto   用户的详细信息
     * @return          新增之后的数据
     */
    @PostMapping
    public ResponseData create(@Valid @RequestBody UserDto userDto){
        return ResponseData.ofSuccess(userService.create(userDto));
    }

    /**
     * 根据id与用户对象修改用户信息
     * @param id            用户的额主键
     * @param userDto       提交的用户的数据
     * @return              处理的结果
     */
    @PutMapping("/{id:\\d+}")
    public ResponseData update(@PathVariable("id") Long id,
                               @Valid @RequestBody UserDto userDto){
        userDto.setId(id);
        return ResponseData.ofSuccess(userService.update(userDto));
    }

    @DeleteMapping("/{id:\\d+}")
    public ResponseData delete(@PathVariable("id") Long id){
        return ResponseData.ofSuccess(userService.delete(id));
    }
}

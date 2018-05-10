package com.berg.fastsearch.core.account.web.controllers;

import com.berg.fastsearch.core.account.service.IUserService;
import com.berg.fastsearch.core.account.web.dto.UserDto;
import com.berg.fastsearch.core.account.web.dto.UserQueryCondition;
import com.berg.fastsearch.core.system.base.service.IBaseService;
import com.berg.fastsearch.core.system.base.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p></p>
 *
 * @author bo.he02@hand-china.com
 * @version v1.0
 * @apiNote Created on 18-3-18
 */
@RequestMapping(value = {"/account/user"})
@RestController
public class UserController extends BaseController<Long, UserDto, UserQueryCondition> {

    /**
     * 用户的服务
     */
    @Autowired
    private IUserService userService;


    @Override
    protected IBaseService<Long, UserDto, UserQueryCondition> getService() {
        return userService;
    }
}

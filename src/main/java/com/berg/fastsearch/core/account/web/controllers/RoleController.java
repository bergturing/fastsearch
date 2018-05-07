package com.berg.fastsearch.core.account.web.controllers;

import com.berg.fastsearch.core.account.entity.Role;
import com.berg.fastsearch.core.account.service.IRoleService;
import com.berg.fastsearch.core.account.web.dto.RoleDto;
import com.berg.fastsearch.core.account.web.dto.RoleQueryCondition;
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
@RequestMapping(value = {"/account/role"})
@RestController
public class RoleController extends BaseController<Long, RoleDto, Role, RoleQueryCondition> {
    /**
     * 用户的服务
     */
    @Autowired
    private IRoleService roleService;


    @Override
    protected IBaseService<RoleDto, Role, Long> getService() {
        return roleService;
    }
}

package com.berg.fastsearch.admin.account.controller;

import com.berg.fastsearch.core.account.service.IRoleService;
import com.berg.fastsearch.core.account.service.IUserRoleService;
import com.berg.fastsearch.core.account.service.IUserService;
import com.berg.fastsearch.core.system.base.web.controller.BaseUrlController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p></p>
 *
 * @author bergturing@qq.com
 * @version v1.0
 * @apiNote Created on 18-5-7
 */
@RequestMapping("/admin/account/userrole")
@Controller
public class AdminUserRoleUrlController extends BaseUrlController<Long> {

    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IUserRoleService userRoleService;

    @Override
    protected String getPrefix() {
        return "admin/account/userrole";
    }

    @Override
    protected void addData(Model model) {
        baseData(model);
    }

    @Override
    protected void editData(Long id, Model model) {
        baseData(model);
        //设置用户角色信息
        model.addAttribute("userRole", userRoleService.findOne(id));
    }

    /**
     * 基础的数据
     * @param model     model 对象
     */
    private void baseData(final Model model){
        //设置所有的角色
        model.addAttribute("roles", roleService.findAll(null));
        //设置所有的用户
        model.addAttribute("users", userService.findAll(null));
    }
}

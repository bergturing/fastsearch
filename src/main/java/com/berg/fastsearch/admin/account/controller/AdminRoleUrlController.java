package com.berg.fastsearch.admin.account.controller;

import com.berg.fastsearch.core.account.service.IRoleService;
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
@RequestMapping("/admin/account/role")
@Controller
public class AdminRoleUrlController extends BaseUrlController<Long> {

    @Autowired
    private IRoleService roleService;

    @Override
    protected String getPrefix() {
        return "admin/account/role";
    }

    @Override
    protected void editData(Long id, Model model) {
        //设置角色信息
        model.addAttribute("role", roleService.findOne(id));
    }
}

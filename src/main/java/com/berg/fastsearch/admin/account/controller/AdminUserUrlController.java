package com.berg.fastsearch.admin.account.controller;

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
@RequestMapping("/admin/account/user")
@Controller
public class AdminUserUrlController extends BaseUrlController<Long> {

    @Autowired
    private IUserService userService;

    @Override
    protected String getPrefix() {
        return "admin/account/user";
    }

    @Override
    protected void editData(Long id, Model model) {
        //添加用户信息
        model.addAttribute("user", userService.findOne(id));
    }
}

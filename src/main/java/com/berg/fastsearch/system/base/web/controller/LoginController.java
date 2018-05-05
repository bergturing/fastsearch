package com.berg.fastsearch.system.base.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p></p>
 *
 * @author bo.he02@hand-china.com
 * @version v1.0
 * @apiNote Created on 18-3-18
 */
@Controller
public class LoginController {
    /**
     * 管理员登录界面
     * @return  管理员登录界面
     */
    @GetMapping(value = {"/login", "/admin/login"})
    public String adminLoginPage(){
        return "admin/login";
    }

    @GetMapping(value = {"/admin/index"})
    public String adminIndexPage(){
        return "admin/index";
    }
}

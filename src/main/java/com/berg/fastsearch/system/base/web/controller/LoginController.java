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

    /**
     * 管理员主页
     * @return  管理员主页
     */
    @GetMapping(value = {"/admin/index"})
    public String adminIndexPage(){
        return "admin/index";
    }

    /**
     * 用户登录界面
     * @return  用户登录界面
     */
    @GetMapping(value = {"/user/login"})
    public String userLoginPage(){
        return "user/login";
    }

    /**
     * 用户主页
     * @return  用户主页
     */
    @GetMapping(value = {"/user/index"})
    public String userIndexPage(){
        return "user/index";
    }
}

package com.berg.fastsearch.admin.system.base.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p></p>
 *
 * @author bo.he02@hand-china.com
 * @version v1.0
 * @apiNote Created on 18-5-7
 */
@Controller
public class AdminBaseUrlController {
    /**
     * 管理员登录界面
     * @return  管理员登录界面
     */
    @GetMapping(value = {"/admin/login"})
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
}

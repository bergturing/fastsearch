package com.berg.fastsearch.user.system.base.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * <p></p>
 *
 * @author bo.he02@hand-china.com
 * @version v1.0
 * @apiNote Created on 18-5-7
 */
@Controller
public class UserBaseUrlController {
    /**
     * 用户登录界面
     * @return  用户登录界面
     */
    @GetMapping(value = {"/login", "/user/login"})
    public String userLoginPage(){
        return "user/login";
    }

    /**
     * 用户主页
     * @return  用户主页
     */
    @GetMapping(value = {"/index", "/user/index"})
    public String userIndexPage(){
        return "user/index";
    }
}

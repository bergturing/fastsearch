package com.berg.fastsearch.user.system.base.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p></p>
 *
 * @author bergturing@qq.com
 * @version v1.0
 * @apiNote Created on 18-5-7
 */
@RequestMapping(value = {"", "/user"})
@Controller
public class UserBaseUrlController {
    /**
     * 用户登录界面
     * @return  用户登录界面
     */
    @GetMapping("/login")
    public String userLoginPage(){
        return "user/login";
    }

    /**
     * 用户主页
     * @return  用户主页
     */
    @GetMapping(value = {"", "/index"})
    public String userIndexPage(){
        return "user/index";
    }
}

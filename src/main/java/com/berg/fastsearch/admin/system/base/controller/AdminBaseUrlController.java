package com.berg.fastsearch.admin.system.base.controller;

import com.berg.fastsearch.core.account.service.IRoleService;
import com.berg.fastsearch.core.account.service.IUserService;
import com.berg.fastsearch.core.account.web.dto.RoleDto;
import com.berg.fastsearch.core.account.web.dto.UserDto;
import com.berg.fastsearch.core.utils.AccountUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p></p>
 *
 * @author bo.he02@hand-china.com
 * @version v1.0
 * @apiNote Created on 18-5-7
 */
@Controller
public class AdminBaseUrlController {
    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;

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
        //用户信息
        UserDto userDto = userService.findByName(AccountUtil.getLoginUserName());

        if(null != userDto){
            //获取用户的权限
            List<RoleDto> roleDtoList = roleService.findByUserId(userDto.getId());

            //获取所有的角色
            Set<String> roles = new HashSet<>();
            if(CollectionUtils.isNotEmpty(roleDtoList)){
                for (RoleDto roleDto : roleDtoList) {
                    roles.add(roleDto.getCode());
                }
            }

            if(roles.contains("SUPER_ADMIN")){
                //超级管理员
                return "admin/index/super";
            } else if(roles.contains("BUSINESS_ADMIN")){
                //业务专员
                return "admin/index/business";
            }else if(roles.contains("CUSTOMER_ADMIN")){
                //客户专员
                return "admin/index/customer";
            }
        }


        return "admin/login";
    }
}

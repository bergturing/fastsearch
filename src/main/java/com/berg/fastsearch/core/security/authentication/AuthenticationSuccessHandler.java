package com.berg.fastsearch.core.security.authentication;

import com.berg.fastsearch.core.account.service.IRoleService;
import com.berg.fastsearch.core.account.service.IUserService;
import com.berg.fastsearch.core.account.web.dto.RoleDto;
import com.berg.fastsearch.core.account.web.dto.UserDto;
import com.berg.fastsearch.core.utils.AccountUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p></p>
 *
 * @author bergturing@qq.com
 * @version v1.0
 * @apiNote Created on 18-5-5
 */
@Component("authenticationSuccessHandler")
public class AuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        logger.info("登录成功");

        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(authentication));

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

            //如果角色为用户,就跳转到用户的主页面
            if(roles.contains("USER")){
                response.sendRedirect("/user/index");
            }else{
                response.sendRedirect("/admin/index");
            }
        }
    }
}

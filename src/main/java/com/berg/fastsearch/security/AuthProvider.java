package com.berg.fastsearch.security;

import com.berg.fastsearch.account.entity.User;
import com.berg.fastsearch.account.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

/**
 * <p></p>
 *
 * @author bo.he02@hand-china.com
 * @version v1.0
 * @apiNote Created on 18-3-18
 */
public class AuthProvider implements AuthenticationProvider{
    @Autowired
    private IUserService userService;

    private final Md5PasswordEncoder passwordEncoder = new Md5PasswordEncoder();

    /**
     * 自定义认证实现逻辑
     * @param authentication
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userName = authentication.getName();
        String inputPassword = (String) authentication.getCredentials();

        //在数据库中查找到用户
        User user = userService.findUsserByName(userName);

        //没有找到用户
        if(null == user){
            throw new AuthenticationCredentialsNotFoundException("authError");
        }

        //判断验证是否通过
        if(passwordEncoder.isPasswordValid(user.getPassword(), inputPassword, user.getId())){
            //验证通过
            return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        }

        //验证失败
        throw new BadCredentialsException("authError");
    }

    /**
     * 支持所有的认证类
     * @param authentication
     * @return
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}

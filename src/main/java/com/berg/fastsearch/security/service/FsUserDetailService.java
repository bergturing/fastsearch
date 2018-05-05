package com.berg.fastsearch.security.service;

import com.berg.fastsearch.account.service.IRoleService;
import com.berg.fastsearch.account.service.IUserService;
import com.berg.fastsearch.account.web.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * <p></p>
 *
 * @author bo.he02@hand-china.com
 * @version v1.0
 * @apiNote Created on 18-5-4
 */
@Component
public class FsUserDetailService implements UserDetailsService {
    /**
     * 用户的服务对象
     */
    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        //在数据库中查找到用户
        UserDto userDto = userService.findByName(userName);

        //没有找到用户
        if(null == userDto){
            return null;
        }

        return new User(userDto.getName(), userDto.getPassword(), roleService.getAuthority(userDto.getId()));
    }
}

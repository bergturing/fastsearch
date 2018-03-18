package com.berg.fastsearch.account.service.impl;

import com.berg.fastsearch.account.entity.Role;
import com.berg.fastsearch.account.entity.User;
import com.berg.fastsearch.account.mapper.UserMapper;
import com.berg.fastsearch.account.service.IUserRoleService;
import com.berg.fastsearch.account.service.IUserService;
import com.berg.fastsearch.system.service.IBaseService;
import com.berg.fastsearch.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>用户服务的实现类</p>
 *
 * @author bo.he02@hand-china.com
 * @version v1.0
 * @apiNote Created on 18-3-18
 */
@Service
public class UserService extends BaseServiceImpl<User> implements IUserService {
    /**
     * 用户角色服务对象
     */
    @Autowired
    private IUserRoleService userRoleService;

    @Override
    public User findUsserByName(String userName) {
        User user = new User();
        user.setName(userName);

        List<User> select = this.select(user);

        //是否找到用户
        if(!CollectionUtils.isEmpty(select) && 1==select.size()){
            user = select.get(0);

            //获取用户所有的权限对象
            List<Role> roleList = userRoleService.findRoleByUserId(user.getId());

            //判断用户是否有权限
            if(CollectionUtils.isEmpty(roleList)){
                throw new DisabledException("权限非法");
            }else{
                //用户权限列表
                List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
                //处理用户权限
                roleList.forEach(role -> {
                    grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
                });

                //设置用户权限列表
                user.setAuthorityList(grantedAuthorities);

                //返回用户
                return user;
            }
        }else{
            //没有找到用户
            return null;
        }
    }
}

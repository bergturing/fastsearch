package com.berg.fastsearch.account.service.impl;

import com.berg.fastsearch.account.dto.RoleDto;
import com.berg.fastsearch.account.dto.UserDto;
import com.berg.fastsearch.account.entity.User;
import com.berg.fastsearch.account.repository.UserRepository;
import com.berg.fastsearch.account.service.IRoleService;
import com.berg.fastsearch.account.service.IUserRoleService;
import com.berg.fastsearch.account.service.IUserService;
import com.berg.fastsearch.system.service.impl.AbstractBaseServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
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
public class UserServiceImpl extends AbstractBaseServiceImpl<UserDto, User, Long> implements IUserService {

    /**
     * 用户的repository对象
     */
    @Autowired
    private UserRepository userRepository;

    /**
     * 角色的服务对象
     */
    @Autowired
    private IRoleService roleService;

    /**
     * 用户角色服务对象
     */
    @Autowired
    private IUserRoleService userRoleService;

    @Override
    public UserDto findByName(String userName) {
        User user = userRepository.findByName(userName);

        //是否找到用户
        if(null != user){
            UserDto userDto = new UserDto();
            BeanUtils.copyProperties(user, userDto);
            //获取用户所有的权限对象
            List<RoleDto> roleDtoList = roleService.findByUserId(user.getId());

            //判断用户是否有权限
            if(CollectionUtils.isEmpty(roleDtoList)){
                throw new DisabledException("权限非法");
            }else{
                //用户权限列表
                List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
                //处理用户权限
                roleDtoList.forEach(roleDto -> {
                    grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + roleDto.getName()));
                });

                //设置用户权限列表
                userDto.setAuthorityList(grantedAuthorities);

                //返回用户
                return userDto;
            }
        }else{
            //没有找到用户
            return null;
        }
    }

    @Override
    protected JpaRepository<User, Long> getRepository() {
        return userRepository;
    }

    @Override
    protected UserDto createDto() {
        return new UserDto();
    }

    @Override
    protected User createEntity() {
        return new User();
    }
}

package com.berg.fastsearch.account.service.impl;

import com.berg.fastsearch.account.entity.Role;
import com.berg.fastsearch.account.entity.UserRole;
import com.berg.fastsearch.account.mapper.UserRoleMapper;
import com.berg.fastsearch.account.service.IRoleService;
import com.berg.fastsearch.account.service.IUserRoleService;
import com.berg.fastsearch.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>用户角色服务实现类</p>
 *
 * @author bo.he02@hand-china.com
 * @version v1.0
 * @apiNote Created on 18-3-18
 */
@Service
public class UserRoleService extends BaseServiceImpl<UserRole> implements IUserRoleService {
    /**
     * 角色服务
     */
    @Autowired
    private IRoleService roleService;

    @Override
    public List<Role> findRoleByUserId(Long userId) {
        UserRole condition = new UserRole();
        condition.setUserId(userId);
        //获取用户和角色的关联数据
        List<UserRole> userRoleList = this.select(condition);

        //角色列表
        List<Role> roleList = new ArrayList<>();
        //查询用户所拥有的角色
        userRoleList.forEach(userRole -> {
            Role role = new Role();
            role.setId(userRole.getRoleId());
            roleList.add(roleService.selectByPrimaryKey(role));
        });

        //返回角色列表
        return roleList;
    }
}

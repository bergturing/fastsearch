package com.berg.fastsearch.account.service.impl;

import com.berg.fastsearch.account.dto.RoleDto;
import com.berg.fastsearch.account.dto.UserRoleDto;
import com.berg.fastsearch.account.entity.Role;
import com.berg.fastsearch.account.repository.RoleRepository;
import com.berg.fastsearch.account.service.IRoleService;
import com.berg.fastsearch.account.service.IUserRoleService;
import com.berg.fastsearch.system.service.impl.AbstractBaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>角色服务实现类</p>
 *
 * @author bo.he02@hand-china.com
 * @version v1.0
 * @apiNote Created on 18-3-18
 */
@Service
public class RoleServiceImpl extends AbstractBaseServiceImpl<RoleDto, Role, Long> implements IRoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private IUserRoleService userRoleService;

    @Override
    public List<RoleDto> findByUserId(Long userId) {
        //获取用户和角色的关联数据
        List<UserRoleDto> userRoleDtoList = userRoleService.findByUserId(userId);

        //角色列表
        List<RoleDto> roleDtoList = new ArrayList<>();
        //查询用户所拥有的角色
        userRoleDtoList.forEach(userRole -> {
            roleDtoList.add(transform2D(roleRepository.findOne(userRole.getRoleId())));
        });

        //返回角色列表
        return roleDtoList;
    }

    @Override
    protected JpaRepository<Role, Long> getRepository() {
        return roleRepository;
    }

    @Override
    protected RoleDto createDto() {
        return new RoleDto();
    }

    @Override
    protected Role createEntity() {
        return new Role();
    }
}

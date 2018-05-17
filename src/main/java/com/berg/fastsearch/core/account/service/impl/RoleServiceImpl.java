package com.berg.fastsearch.core.account.service.impl;

import com.berg.fastsearch.core.account.web.dto.RoleDto;
import com.berg.fastsearch.core.account.web.dto.RoleQueryCondition;
import com.berg.fastsearch.core.account.web.dto.UserRoleDto;
import com.berg.fastsearch.core.account.entity.Role;
import com.berg.fastsearch.core.account.repository.RoleRepository;
import com.berg.fastsearch.core.account.service.IRoleService;
import com.berg.fastsearch.core.account.service.IUserRoleService;
import com.berg.fastsearch.core.system.base.service.impl.AbstractBaseServiceImpl;
import org.elasticsearch.action.search.SearchRequestBuilder;
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
 * <p>角色服务实现类</p>
 *
 * @author bo.he02@hand-china.com
 * @version v1.0
 * @apiNote Created on 18-3-18
 */
@Service
public class RoleServiceImpl
        extends AbstractBaseServiceImpl<Long, RoleDto, Role, RoleQueryCondition>
        implements IRoleService {

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
    public List<GrantedAuthority> getAuthority(Long userId) {
        //获取用户所有的权限对象
        List<RoleDto> roleDtoList = this.findByUserId(userId);

        //判断用户是否有权限
        if(CollectionUtils.isEmpty(roleDtoList)){
            throw new DisabledException("权限非法");
        }else{
            //用户权限列表
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            //处理用户权限
            roleDtoList.forEach(roleDto -> {
                grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + roleDto.getCode()));
            });

            //返回用户
            return grantedAuthorities;
        }
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

    @Override
    protected void transform2E(RoleDto dto, Role entity) throws Exception {
        //如果dto有Id,就设置用于数据的更新
        Long id = dto.getId();
        if(id!=null && id>0){
            //更新
            entity.setId(id);
        }else{
            //新建
        }
    }

}

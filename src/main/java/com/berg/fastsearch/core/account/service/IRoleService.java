package com.berg.fastsearch.core.account.service;

import com.berg.fastsearch.core.account.entity.Role;
import com.berg.fastsearch.core.account.web.dto.RoleDto;
import com.berg.fastsearch.core.system.base.service.IBaseService;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

/**
 * <p>角色服务接口</p>
 *
 * @author bo.he02@hand-china.com
 * @version v1.0
 * @apiNote Created on 18-3-18
 */
public interface IRoleService extends IBaseService<RoleDto, Role, Long> {

    /**
     * 根据用户的id来查找其所拥有的权限对象
     * @param userId        用户id
     * @return              所有的权限对象
     */
    List<RoleDto> findByUserId(Long userId);

    /**
     * 根据用户的主键获取用户的权限列表
     * @param userId    用户的主键
     * @return          用户的权限列表
     */
    List<GrantedAuthority> getAuthority(Long userId);
}

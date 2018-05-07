package com.berg.fastsearch.core.account.web.dto;

import com.berg.fastsearch.core.system.base.web.dto.BaseDto;

import javax.validation.constraints.NotNull;

/**
 * <p></p>
 *
 * @author bo.he02@hand-china.com
 * @version v1.0
 * @apiNote Created on 18-5-3
 */
public class UserRoleDto extends BaseDto<Long> {
    private static final long serialVersionUID = 6943882100632115633L;

    /**
     * 用户id,用户表(sys_users)的主键
     */
    @NotNull
    private Long userId;

    /**
     * 角色的id,角色表(sys_roles)的主键
     */
    @NotNull
    private Long roleId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}

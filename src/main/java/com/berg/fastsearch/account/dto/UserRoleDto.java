package com.berg.fastsearch.account.dto;

import com.berg.fastsearch.system.dto.BaseDto;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * <p></p>
 *
 * @author bo.he02@hand-china.com
 * @version v1.0
 * @apiNote Created on 18-5-3
 */
public class UserRoleDto extends BaseDto{
    private static final long serialVersionUID = 6943882100632115633L;

    /**
     * 角色分派的主键
     */
    private Long id;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

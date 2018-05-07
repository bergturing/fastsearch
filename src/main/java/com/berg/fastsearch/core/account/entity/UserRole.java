package com.berg.fastsearch.core.account.entity;

import com.berg.fastsearch.core.system.base.entity.BaseEntity;

import javax.persistence.*;

/**
 * <p>用户角色对象</p>
 *
 * @author bo.he02@hand-china.com
 * @version v1.0
 * @apiNote Created on 18-3-18
 */
@Table(
        name = "sys_user_role"
)
@Entity
public class UserRole extends BaseEntity {
    /**
     * 角色分派的主键
     */
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    /**
     * 用户id,用户表(sys_users)的主键
     */
    @Column
    private Long userId;

    /**
     * 角色的id,角色表(sys_roles)的主键
     */
    @Column
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

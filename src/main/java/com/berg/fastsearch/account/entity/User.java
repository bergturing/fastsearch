package com.berg.fastsearch.account.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * <p>用户的对象表</p>
 *
 * @author bo.he02@hand-china.com
 * @version v1.0
 * @apiNote Created on 18-3-18
 */
@Table(
        name = "sys_users"
)
@Entity
public class User {
    /**
     * 用户的主键
     */
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    /**
     * 用户名称
     */
    @Column
    private String name;

    /**
     * 用户邮箱
     */
    @Column
    private String email;

    /**
     * 用户的手机号
     */
    @NotNull
    @Column
    private String phoneNumber;

    /**
     * 用户密码
     */
    @Column
    private String password;

    /**
     * 用户的状态:NORMAL-正常,BAN-封禁
     * 编码维护code: FS.ACCOUNT_USER_STATUS
     */
    @NotNull
    @Column
    private String status;

    /**
     * 用户头像
     */
    @Column
    private String avatar;

    /**
     * 用户创建时间
     */
    @Column
    private Date createTime;

    /**
     * 最近登录时间
     */
    @Column
    private Date lastLoginTime;

    /**
     * 上次更新记录时间
     */
    @Column
    private Date lastUpdateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }
}

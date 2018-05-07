package com.berg.fastsearch.account.web.dto;

import com.berg.fastsearch.system.base.web.dto.BaseDto;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * <p></p>
 *
 * @author bo.he02@hand-china.com
 * @version v1.0
 * @apiNote Created on 18-5-3
 */
public class UserDto extends BaseDto <Long>{
    private static final long serialVersionUID = 6002603646559377214L;

    /**
     * 用户名称
     */
    @Length(max = 32)
    private String name;

    /**
     * 用户邮箱
     */
    @Length(max = 32)
    private String email;

    /**
     * 用户的手机号
     */
    @NotNull
    @Length(max = 15)
    private String phoneNumber;

    /**
     * 用户密码
     */
    @Length(max = 32)
    private String password;

    /**
     * 用户的状态:NORMAL-正常,BAN-封禁
     * 编码维护code: FS.ACCOUNT_USER_STATUS
     */
    @Length(max = 32)
    private String status;

    /**
     * 用户头像
     */
    @Length(max = 255)
    private String avatar;

    /**
     * 最近登录时间
     */
    private Date lastLoginTime;

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

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }
}

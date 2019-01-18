package com.berg.fastsearch.core.system.validatecode.entity;

import java.time.LocalDateTime;

/**
 * <p></p>
 *
 * @author bergturing@qq.com
 * @version v1.0
 * @apiNote Created on 18-5-4
 */
public class ValidateCode {
    /**
     * 验证码的值
     */
    private String code;

    /**
     * 验证码的过期时间
     */
    private LocalDateTime expireTime;

    public ValidateCode(String code, int expireIn){
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }

    public ValidateCode(String code, LocalDateTime expireTime){
        this.code = code;
        this.expireTime = expireTime;
    }

    public boolean isExpried() {
        return LocalDateTime.now().isAfter(expireTime);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(LocalDateTime expireTime) {
        this.expireTime = expireTime;
    }
}

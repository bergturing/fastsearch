package com.berg.fastsearch.core.system.validatecode.entity;

/**
 * <p></p>
 *
 * @author bergturing@qq.com
 * @version v1.0
 * @apiNote Created on 18-5-18
 */
public class SmsCode extends ValidateCode {
    public SmsCode(String code, int expireIn) {
        super(code, expireIn);
    }
}

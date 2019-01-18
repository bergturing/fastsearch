package com.berg.fastsearch.core.system.validatecode.generator.impl;

import com.berg.fastsearch.core.system.validatecode.entity.SmsCode;
import com.berg.fastsearch.core.system.validatecode.generator.ValidateCodeGenerator;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.Date;
import java.util.Random;

/**
 * <p></p>
 *
 * @author bergturing@qq.com
 * @version v1.0
 * @apiNote Created on 18-5-18
 */
@Component("smsCodeGenerator")
public class SmsCodeGenerator implements ValidateCodeGenerator<SmsCode> {

    private static final Integer SMS_LENGTH = 6;

    @Override
    public SmsCode generate(ServletWebRequest request) {
        Random random = new Random((new Date()).getTime());
        StringBuilder sRand = new StringBuilder("");
        for (int i = 0; i < SMS_LENGTH; i++) {
            String rand = String.valueOf(random.nextInt(10));
            sRand.append(rand);
        }

        //返回生成的验证码
        return new SmsCode(sRand.toString(), DEFAULT_EXPIRE_IN);
    }
}

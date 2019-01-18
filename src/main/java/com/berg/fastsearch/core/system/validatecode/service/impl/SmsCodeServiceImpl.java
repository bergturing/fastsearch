package com.berg.fastsearch.core.system.validatecode.service.impl;

import com.berg.fastsearch.core.system.validatecode.entity.SmsCode;
import com.berg.fastsearch.core.system.validatecode.generator.ValidateCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * <p></p>
 *
 * @author bergturing@qq.com
 * @version v1.0
 * @apiNote Created on 18-5-18
 */
@Service("smsCodeServiceImpl")
public class SmsCodeServiceImpl extends AbstractValidateCodeService<SmsCode>{

    @Autowired
    private ValidateCodeGenerator<SmsCode> smsCodeGenerator;

    @Override
    protected SmsCode generate(ServletWebRequest request) {
        return smsCodeGenerator.generate(request);
    }

    @Override
    protected void send(ServletWebRequest request, SmsCode validateCode) throws Exception {

    }
}

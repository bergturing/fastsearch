package com.berg.fastsearch.system.validatecode.service;

import com.berg.fastsearch.system.validatecode.entity.ValidateCode;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * <p></p>
 *
 * @author bo.he02@hand-china.com
 * @version v1.0
 * @apiNote Created on 18-5-4
 */
public interface IValidateCodeService<CODE extends ValidateCode> {

    /**
     * 验证码放入session时的前缀
     */
    String SESSION_KEY = "SESSION_KEY_FOR_VALIDATE_CODE";

    /**
     *
     */
    String REQUEST_PARAMETER_NAME = "validateCode";

    /**
     * 创建校验码
     *
     * @param request       请求域对象
     * @throws Exception    处理异常
     */
    void create(ServletWebRequest request) throws Exception;

    /**
     * 校验验证码
     *
     * @param servletWebRequest     请求域对象
     */
    void validate(ServletWebRequest servletWebRequest);
}

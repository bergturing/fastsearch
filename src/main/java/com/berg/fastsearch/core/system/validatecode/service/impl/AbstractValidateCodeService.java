package com.berg.fastsearch.core.system.validatecode.service.impl;

import com.berg.fastsearch.core.system.validatecode.entity.ValidateCode;
import com.berg.fastsearch.core.system.validatecode.exception.ValidateCodeException;
import com.berg.fastsearch.core.system.validatecode.service.IValidateCodeService;
import org.apache.commons.lang.StringUtils;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * <p></p>
 *
 * @author bo.he02@hand-china.com
 * @version v1.0
 * @apiNote Created on 18-5-4
 */
public abstract class AbstractValidateCodeService<CODE extends ValidateCode> implements IValidateCodeService<CODE> {

    /**
     * 操作session的工具类
     */
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Override
    public void create(ServletWebRequest request) throws Exception {
        CODE validateCode = generate(request);
        save(request, validateCode);
        send(request, validateCode);
    }

    @Override
    public void validate(ServletWebRequest request) {
        CODE codeInSession = (CODE) sessionStrategy.getAttribute(request, SESSION_KEY);

        String codeInRequest;
        try {
            codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(), REQUEST_PARAMETER_NAME);
        } catch (ServletRequestBindingException e) {
            throw new ValidateCodeException("获取验证码的值失败");
        }

        if (StringUtils.isBlank(codeInRequest)) {
            throw new ValidateCodeException(REQUEST_PARAMETER_NAME + "验证码的值不能为空");
        }

        if (codeInSession == null) {
            throw new ValidateCodeException(REQUEST_PARAMETER_NAME + "验证码不存在");
        }

        if (codeInSession.isExpried()) {
            sessionStrategy.removeAttribute(request, SESSION_KEY);
            throw new ValidateCodeException(REQUEST_PARAMETER_NAME + "验证码已过期");
        }

        if (!StringUtils.equals(codeInSession.getCode(), codeInRequest)) {
            throw new ValidateCodeException(REQUEST_PARAMETER_NAME + "验证码不匹配");
        }

        sessionStrategy.removeAttribute(request, SESSION_KEY);
    }

    /**
     * 保存校验码
     *
     * @param request           请求域对象
     * @param validateCode      验证码对象
     */
    private void save(ServletWebRequest request, CODE validateCode) {
        sessionStrategy.setAttribute(request, SESSION_KEY, validateCode);
    }

    /**
     * 生成校验码
     *
     * @param request       请求域对象
     * @return              生成的验证码
     */
    protected abstract CODE generate(ServletWebRequest request);

    /**
     * 发送校验码，由子类实现
     *
     * @param request           请求域对象
     * @param validateCode      验证码对象
     * @throws Exception        处理的异常
     */
    protected abstract void send(ServletWebRequest request, CODE validateCode) throws Exception;
}

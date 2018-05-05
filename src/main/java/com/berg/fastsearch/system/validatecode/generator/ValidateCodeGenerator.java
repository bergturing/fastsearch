package com.berg.fastsearch.system.validatecode.generator;

import com.berg.fastsearch.system.validatecode.entity.ValidateCode;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * <p></p>
 *
 * @author bo.he02@hand-china.com
 * @version v1.0
 * @apiNote Created on 18-5-4
 */
public interface ValidateCodeGenerator<CODE extends ValidateCode> {

    /**
     * 默认的验证码的宽度
     */
    Integer DEFAULT_WIDTH = 80;

    /**
     * 默认的验证码的高度
     */
    Integer DEFAULT_HEIGHT = 36;

    /**
     * 默认的验证码的长度
     */
    Integer DEFAULT_LENGTH = 4;

    /**
     * 默认验证码失效的时间(s)
     */
    Integer DEFAULT_EXPIRE_IN = 60;

    /**
     * 生成验证码
     * @param request       请求域对象
     * @return              生成的验证码对象
     */
    CODE generate(ServletWebRequest request);
}

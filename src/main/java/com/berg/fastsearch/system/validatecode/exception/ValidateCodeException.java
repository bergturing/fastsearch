package com.berg.fastsearch.system.validatecode.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * <p></p>
 *
 * @author bo.he02@hand-china.com
 * @version v1.0
 * @apiNote Created on 18-5-4
 */
public class ValidateCodeException extends AuthenticationException {

    private static final long serialVersionUID = -6218565756349156136L;

    public ValidateCodeException(String msg) {
        super(msg);
    }
}

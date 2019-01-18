package com.berg.fastsearch.core.system.validatecode.web.filter;

import com.berg.fastsearch.core.system.validatecode.exception.ValidateCodeException;
import com.berg.fastsearch.core.system.validatecode.service.IValidateCodeService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * <p></p>
 *
 * @author bergturing@qq.com
 * @version v1.0
 * @apiNote Created on 18-5-5
 */
@Component("imageCodeFilter")
public class ImageCodeFilter extends OncePerRequestFilter implements InitializingBean {

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * url的set
     */
    private Set<String> urlSet = new HashSet<>();

    /**
     * 验证请求url与配置的url是否匹配的工具类
     */
    private AntPathMatcher pathMatcher = new AntPathMatcher();

    @Autowired
    private IValidateCodeService imageCodeServiceImpl;

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    /**
     * 初始化要拦截的url配置信息
     */
    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();

        urlSet.add("/authentication/form");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        if(needValidate(request)){
            try {
                imageCodeServiceImpl.validate(new ServletWebRequest(request, response));
                logger.info("验证码校验通过");
            } catch (ValidateCodeException exception) {
                authenticationFailureHandler.onAuthenticationFailure(request, response, exception);
                return;
            }
        }

        filterChain.doFilter(request, response);
    }

    /**
     * 获取校验码的类型，如果当前请求不需要校验，则返回null
     *
     * @param request
     * @return
     */
    private boolean needValidate(HttpServletRequest request) {
        return (!StringUtils.equalsIgnoreCase(request.getMethod(), "get") &&
                urlSet.contains(request.getRequestURI()));
    }
}

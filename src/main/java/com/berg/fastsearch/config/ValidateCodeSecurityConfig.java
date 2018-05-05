package com.berg.fastsearch.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;

/**
 * <p></p>
 *
 * @author bo.he02@hand-china.com
 * @version v1.0
 * @apiNote Created on 18-5-5
 */
@Component("validateCodeSecurityConfig")
public class ValidateCodeSecurityConfig  extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    @Autowired
    private Filter imageCodeFilter;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(imageCodeFilter, AbstractPreAuthenticatedProcessingFilter.class);
    }
}

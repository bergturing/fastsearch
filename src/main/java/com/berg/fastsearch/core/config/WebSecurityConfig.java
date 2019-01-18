package com.berg.fastsearch.core.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

/**
 * <p></p>
 *
 * @author bergturing@qq.com
 * @version v1.0
 * @apiNote Created on 18-3-18
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 默认记住我的时间
     */
    private static final Integer DEFAULT_REMEMBER_ME_SECONDS = 3600;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;

    /**
     * HTTP权限控制
     *
     * @param http 权限控制对象
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.apply(validateCodeSecurityConfig)
                .and()
            //处理表单登录
            .formLogin()
                //登录入口
                .loginPage("/admin/login")
                //配置角色登录处理入口
                .loginProcessingUrl("/authentication/form")
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler)
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .and()
            //记住我
            .rememberMe()
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(DEFAULT_REMEMBER_ME_SECONDS)
                .userDetailsService(userDetailsService)
                .and()
            //处理请求
            .authorizeRequests()
                .antMatchers(
                        //管理员登录界面
                        "/admin/login",
                        //用户登录入口
                        "/login",
                        "/user/login",
                        //静态资源
                        "/static/**",
                        //获取验证码
                        "/image/code",
                        "/sms/code"
                    ).permitAll()
                .antMatchers(
                        //其他管理员界面
                        "/admin/**"
                    ).hasAnyRole("SUPER_ADMIN", "BUSINESS_ADMIN", "CUSTOMER_ADMIN")
                .antMatchers(
                        //其他用户界面
                        "/**",
                        "/user/**",
                        //接口
                        "/api/user/**"
                    ).hasAnyRole("SUPER_ADMIN", "BUSINESS_ADMIN", "CUSTOMER_ADMIN", "USER")
                .anyRequest()
                .authenticated()
                .and()
            //同源策略
            .headers()
                .frameOptions()
                .sameOrigin()
                .and()
            //禁用csrf
            .csrf()
                .disable();
    }

    /**
     * 注入密码解析对象
     *
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
//		tokenRepository.setCreateTableOnStartup(true);
        return tokenRepository;
    }
}

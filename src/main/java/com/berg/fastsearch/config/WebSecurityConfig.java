package com.berg.fastsearch.config;

import com.berg.fastsearch.security.AuthProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * <p></p>
 *
 * @author bo.he02@hand-china.com
 * @version v1.0
 * @apiNote Created on 18-3-18
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

    /**
     * HTTP权限控制
     * @param http          权限控制对象
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                //管理员登录界面
                .antMatchers("/admin/login").permitAll()
                //静态资源
                .antMatchers("/static/**").permitAll()
                //用户登录入口
                .antMatchers("/user/login").permitAll()
                //其他管理员界面
                .antMatchers("/admin/**").hasRole("ADMIN")
                //其他用户界面
                .antMatchers("/user/**").hasAnyRole("ADMIN", "USER")
                //接口
                .antMatchers("/api/user/**").hasAnyRole("ADMIN", "USER")
                .and()
                .formLogin()
                //配置角色登录处理入口
                .loginProcessingUrl("/login")
                .and();

        http.csrf().disable();
        //同源策略
        http.headers().frameOptions().sameOrigin();
    }

    /**
     * 自定义认证策略
     * @param auth
     */
    @Autowired
    public void configGlobal(AuthenticationManagerBuilder auth) throws Exception {
        //内存认证
//        auth.inMemoryAuthentication()
//                .withUser("admin")
//                .password("admin")
//                .roles("ADMIN")
//                .and();

        //通过自定义的逻辑进行认证
        auth.authenticationProvider(authProvider())
                //擦除密码
                .eraseCredentials(true);
    }

    @Bean
    public AuthProvider authProvider(){
        return new AuthProvider();
    }
}

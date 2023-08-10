package com.lowkey.complex.config;

import com.lowkey.complex.security.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

/*
 * Security配置类
 * 要实现WebSecurityConfigurerAdapter抽象类
 * */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //用户Service，根据前端用户名从数据库中查询用户信息
    final UserDetailsService userDetailsService;
    final LoginFailureHandler loginFailureHandler;
    final LoginSuccessHandler loginSuccessHandler;
    final AnonymousAuthenticationEntryPoint anonymousAuthenticationEntryPoint;
    final InvalidSessionHandler invalidSessionHandler;
    final SessionInformationExpiredHandler sessionInformationExpiredHandler;
    final LoginUserAccessDeniedHandler loginUserAccessDeniedHandler;
    final LogoutSuccessHandler logoutSuccessHandler;

    public SecurityConfig(UserDetailsService userDetailsService, LoginFailureHandler loginFailureHandler, LoginSuccessHandler loginSuccessHandler, AnonymousAuthenticationEntryPoint anonymousAuthenticationEntryPoint, InvalidSessionHandler invalidSessionHandler, SessionInformationExpiredHandler sessionInformationExpiredHandler, LoginUserAccessDeniedHandler loginUserAccessDeniedHandler, LogoutSuccessHandler logoutSuccessHandler) {
        this.userDetailsService = userDetailsService;
        this.loginFailureHandler = loginFailureHandler;
        this.loginSuccessHandler = loginSuccessHandler;
        this.anonymousAuthenticationEntryPoint = anonymousAuthenticationEntryPoint;
        this.invalidSessionHandler = invalidSessionHandler;
        this.sessionInformationExpiredHandler = sessionInformationExpiredHandler;
        this.loginUserAccessDeniedHandler = loginUserAccessDeniedHandler;
        this.logoutSuccessHandler = logoutSuccessHandler;
    }

    /**
     * 此种放行,不走spring security的过滤链条,直接放行
     *
     * @param web WebSecurity
     */
    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/css/**", "/js/**", "/index.html", "/img/**", "/fonts/**", "/favicon.ico", "/verifyCode");
    }

    //配置登录访问的接口
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //关闭csrf防护
        http.cors().disable().csrf().disable()

                .formLogin()
                //登录成功处理逻辑
                .successHandler(loginSuccessHandler)
                //登录失败处理逻辑
                .failureHandler(loginFailureHandler).permitAll()

                //登出,允许所有用户
                .and().logout().deleteCookies("JSESSIONID")
                //登出成功处理逻辑
                .logoutSuccessHandler(logoutSuccessHandler).permitAll()

                .and().authorizeRequests()
                //此种放行接口,还是要进行spring security的过滤链条
                .antMatchers("/test/**").permitAll()
                // 对于登录接口 允许匿名访问
                .antMatchers("/user/login").anonymous()
                // 除上面外的所有请求全部需要鉴权认证
                .anyRequest().authenticated()

                // 异常处理(权限拒绝、登录失效等)
                .and().exceptionHandling()
                //用户访问无权限资源时的异常处理->注意：配置此项则不再初始化默认登录页面,需要自行定义
                .authenticationEntryPoint(anonymousAuthenticationEntryPoint)
                //登录用户没有权限访问资源
                .accessDeniedHandler(loginUserAccessDeniedHandler)
                //会话管理,超时处理
                .and().sessionManagement().invalidSessionStrategy(invalidSessionHandler)
                //同一账号同时登录最大用户数
                .maximumSessions(1)
                //顶号处理
                .expiredSessionStrategy(sessionInformationExpiredHandler);
//        http.formLogin()
//                //登录页面设置
//                .loginPage("/login.html")
//                //登录访问路径设置
//                .loginProcessingUrl("/user/login")
//                //登录成功之后调整的路径
//                .defaultSuccessUrl("/index");
    }

    //配置
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //配置接口与加密方式,默认BCryptPasswordEncoder加密方式
        auth.userDetailsService(userDetailsService).passwordEncoder(new CustomPasswordEncoder());
    }
}
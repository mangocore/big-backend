package com.mangocore.server.config.sys;

import com.mangocore.server.config.filter.AuthenticationFilter;
import com.mangocore.server.config.filter.LoginFilter;
import com.mangocore.server.server.RestAccessDeniedHandler;
import com.mangocore.server.server.RestAuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Created by notreami on 17/9/15.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 设置 HTTP 验证规则
     *
     * @param httpSecurity
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                // 关闭csrf验证
                .csrf().disable()
                // 基于token，所以不需要session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                // 对请求进行认证
                .authorizeRequests()
                // 所有 / 的所有请求 都放行
                .antMatchers("/").permitAll()
                // 所有 /login 的POST请求 都放行
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                // 权限检查
//                .antMatchers("/hello").hasAuthority("AUTH_WRITE")
                // 角色检查
//                .antMatchers("/world").hasRole("ADMIN")
                // 除上面外的所有请求全部需要鉴权认证
                .anyRequest().authenticated().and()
                // 添加一个过滤器 所有访问 /login 的请求交给 JWTLoginFilter 来处理 这个类处理所有的JWT相关内容
                .addFilterBefore(new LoginFilter("/login", authenticationManager()), UsernamePasswordAuthenticationFilter.class)
                // 添加一个过滤器验证其他请求的Token是否合法
                .addFilterBefore(getAuthenticationFilter(), AuthenticationFilter.class)
                //异常抛出，当用户请求的操作需要登录时，将抛出 AuthenticationException 异常
                .exceptionHandling()
                .authenticationEntryPoint(new RestAuthenticationEntryPoint())//需要登陆
                .accessDeniedHandler(new RestAccessDeniedHandler());//没有授权

        // 取消安全报文头
        httpSecurity.headers().disable();
    }

    @Bean
    public AuthenticationFilter getAuthenticationFilter() {
        return new AuthenticationFilter();
    }

}

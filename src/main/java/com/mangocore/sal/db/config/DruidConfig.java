package com.mangocore.sal.db.config;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.alibaba.druid.support.spring.stat.DruidStatInterceptor;
import com.mangocore.sal.db.mapper.SimpleMapper;
import org.springframework.aop.Advisor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.JdkRegexpMethodPointcut;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by notreami on 17/11/27.
 */
@Configuration
public class DruidConfig {

    @Value("${security.user.name}")
    private String loginUsername;
    @Value("${security.user.password}")
    private String loginPassword;


    /**
     * druidWeb监控
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean druidFilterRegistrationBean() {
        FilterRegistrationBean reg = new FilterRegistrationBean(new WebStatFilter());
        reg.addUrlPatterns("/api/*");//添加过滤规则
        //添加不需要忽略的格式信息
//        reg.addInitParameter("exclusions","*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid2/*");
        return reg;
    }

    /**
     * 监听Spring
     * 1.定义拦截器
     * 2.定义切入点
     * 3.定义通知类
     *
     * @return
     */
    @Bean
    public DruidStatInterceptor druidStatInterceptor() {
        return new DruidStatInterceptor();
    }

    /**
     * 添加aop的pointcut
     *
     * @return
     */
    @Bean
    public JdkRegexpMethodPointcut druidStatPointcut() {
        JdkRegexpMethodPointcut druidStatPointcut = new JdkRegexpMethodPointcut();
        String pattern = SimpleMapper.class.getPackage().getName() + ".*";
        druidStatPointcut.setPatterns(pattern);
        return druidStatPointcut;
    }

    @Bean
    public Advisor druidStatAdvisor() {
        return new DefaultPointcutAdvisor(druidStatPointcut(), druidStatInterceptor());
    }

    /**
     * druid Servlet
     *
     * @return
     */
    @Bean
    public ServletRegistrationBean druidServletRegistrationBean() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean();
        servletRegistrationBean.setServlet(new StatViewServlet());
        servletRegistrationBean.addUrlMappings("/druid/*");
        servletRegistrationBean.addInitParameter("loginUsername", loginUsername);// 用户名
        servletRegistrationBean.addInitParameter("loginPassword", loginPassword);// 密码
        servletRegistrationBean.addInitParameter("profileEnable", "true");//监控单个url调用的sql列表
        servletRegistrationBean.addInitParameter("resetEnable", "false");// 禁用HTML页面上的“Reset All”功能
        return servletRegistrationBean;
    }
}

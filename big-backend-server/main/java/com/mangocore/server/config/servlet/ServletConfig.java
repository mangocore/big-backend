package com.mangocore.server.config.servlet;

import com.alibaba.druid.support.http.StatViewServlet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Servlet配置
 * Created by notreami on 17/5/9.
 */
@Configuration
public class ServletConfig {

    @Value("${security.user.name}")
    private String loginUsername;
    @Value("${security.user.password}")
    private String loginPassword;

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

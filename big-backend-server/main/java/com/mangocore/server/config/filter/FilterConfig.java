package com.mangocore.server.config.filter;

import com.alibaba.druid.support.http.WebStatFilter;
import com.alibaba.druid.support.spring.stat.DruidStatInterceptor;
import com.mangocore.data.mapper.SimpleMapper;
import org.springframework.aop.Advisor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.JdkRegexpMethodPointcut;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;

/**
 * 全局拦截配置
 * Created by notreami on 16/8/7.
 */
@Configuration
public class FilterConfig {

    /**
     * 传入数据字符集编码
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean characterEncodingFilterRegistration() {
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);

        FilterRegistrationBean reg = new FilterRegistrationBean();
        reg.setFilter(filter);
        reg.addUrlPatterns("/*");
        return reg;
    }

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

}

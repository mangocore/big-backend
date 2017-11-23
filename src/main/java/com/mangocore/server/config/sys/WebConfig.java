package com.mangocore.server.config.sys;

import com.google.common.collect.Lists;
import com.mangocore.common.util.JsonBinder;
import com.mangocore.server.config.filter.BAInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by notreami on 16/8/7.
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    /**
     * 返回字符编码
     *
     * @return
     */
    private StringHttpMessageConverter customStringHttpMessageConverter() {
        //添加application/json;charset=UTF-8 编码，默认是text/html 编码
        List<MediaType> supportedMediaTypes = Lists.newArrayList();
        supportedMediaTypes.add(MediaType.APPLICATION_JSON);
        supportedMediaTypes.add(MediaType.TEXT_PLAIN);
        supportedMediaTypes.add(MediaType.ALL);

        StringHttpMessageConverter stringConverter = new StringHttpMessageConverter();
        stringConverter.setDefaultCharset(StandardCharsets.UTF_8);
        stringConverter.setSupportedMediaTypes(supportedMediaTypes);
        stringConverter.setWriteAcceptCharset(false);
        return stringConverter;
    }

    /**
     * 扩展现有的消息转换器链表,会被系统后续添加的消息转换器替换
     *
     * @param converters
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
    }

    /**
     * 扩展现有的消息转换器链表,会替换后续系统添加的消息转换器替换
     *
     * @param converters
     */
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        List<HttpMessageConverter<?>> httpMessageConverters = Lists.newArrayList();
        for (HttpMessageConverter<?> httpMessageConverter : converters) {
            if (httpMessageConverter instanceof StringHttpMessageConverter ||
                    httpMessageConverter instanceof MappingJackson2HttpMessageConverter) {
                continue;
            }
            httpMessageConverters.add(httpMessageConverter);
        }
        httpMessageConverters.add(customStringHttpMessageConverter());
        httpMessageConverters.add(JsonBinder.getFastJsonHttpMessageConverter());//使用FastJson

        converters.clear();
        converters.addAll(httpMessageConverters);
    }

    /**
     * 同源策略-CORS跨域
     *
     * @return
     */
    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*"); // 允许所有的外域发起跨域请求
        corsConfiguration.addAllowedHeader("Origin, X-Requested-With, Content-Type, Accept, Location, Authorization, userId, token"); // 允许跨域请求包含的头信息
        corsConfiguration.addAllowedMethod("GET, POST, PUT, OPTIONS, DELETE"); // 允许外域发起请求HTTP Method
        corsConfiguration.setMaxAge(3600L);
        corsConfiguration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(source);
    }

    /**
     * BA认证
     *
     * @return
     */
    @Bean
    public BAInterceptor baFilter() {
        return new BAInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(baFilter());
    }


    /**
     * 替换默认线程池
     *
     * @return
     */
    @Bean
    public Executor customExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(8);//核心线程数，默认为1
        executor.setMaxPoolSize(100);//最大线程数，默认为Integer.MAX_VALUE
        executor.setQueueCapacity(1000);//队列最大长度，一般需要设置值>=notifyScheduledMainExecutor.maxNum；默认为Integer.MAX_VALUE
        executor.setKeepAliveSeconds(60);//线程池维护线程所允许的空闲时间，默认为60s
        executor.setThreadNamePrefix("taskExecutor-");

        // rejection-policy：当pool已经达到max size的时候，如何处理新任务
        // CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
        //线程池对拒绝任务（无线程可用）的处理策略，目前只支持AbortPolicy、CallerRunsPolicy；默认为后者
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }

}

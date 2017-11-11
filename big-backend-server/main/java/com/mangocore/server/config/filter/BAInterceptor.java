package com.mangocore.server.config.filter;

import com.mangocore.common.common.ErrorInfo;
import com.mangocore.common.response.CommonResponse;
import com.mangocore.common.util.JsonBinder;
import com.mangocore.common.util.BasicAuth;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * BA认证
 * Created by notreami on 16/8/8.
 */
@Slf4j
public class BAInterceptor extends HandlerInterceptorAdapter {
    @Value("${custom.mws_client_id}")
    private String client_id;
    @Value("${custom.mws_client_secret}")
    private String client_secret;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        final String path = request.getRequestURI();

        if (!StringUtils.startsWith(path, "/api/")) {
            return true;
        }
        //测试环境可方便调试
        if (StringUtils.isEmpty(client_id) || StringUtils.isEmpty(client_secret)) {
            return true;
        }

        BasicAuth.Data basicAuthData = null;
        String uri = null;
        String method = null;
        try {
            uri = request.getRequestURI();
            method = request.getMethod();

            String date = request.getHeader("Date");
            String authorization = request.getHeader("Authorization");
            basicAuthData = BasicAuth.Data.create(date, authorization);
            if (StringUtils.isNotEmpty(date) && StringUtils.isNotEmpty(authorization)) {
                String clientKey = BasicAuth.getClientKey(basicAuthData.getAuth());//若client_id错误,则无需加密验证(加密耗时),指下一行代码
                if (StringUtils.equals(client_id, clientKey) && BasicAuth.checkBasicAuthData(basicAuthData, client_secret, method, uri)) {
                    return true;
                }
            }
        } catch (Exception e) {
            log.error("BA exception: " + e);
        }
        log.warn("BA failed: " + basicAuthData + " " + method + " " + uri);
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.getWriter().write(JsonBinder.toJSONString(CommonResponse.createError(ErrorInfo.STATUS_INFO_ACCESS)));
        response.getWriter().flush();
        response.getWriter().close();
        return false;
    }

}

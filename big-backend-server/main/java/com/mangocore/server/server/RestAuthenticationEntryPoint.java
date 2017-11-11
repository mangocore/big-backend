package com.mangocore.server.server;

import com.mangocore.common.common.ErrorInfo;
import com.mangocore.common.response.CommonResponse;
import com.mangocore.common.util.JsonBinder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by notreami on 17/10/29.
 */
@Slf4j
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        log.warn("Authentication Failed: " + authException.getMessage());

        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.getWriter().write(JsonBinder.toJSONString(CommonResponse.createError(ErrorInfo.STATUS_INFO_AUTH)));
        response.getWriter().flush();
        response.getWriter().close();
    }
}

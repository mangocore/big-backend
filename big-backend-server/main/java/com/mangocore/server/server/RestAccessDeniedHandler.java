package com.mangocore.server.server;

import com.mangocore.common.common.ErrorInfo;
import com.mangocore.common.response.CommonResponse;
import com.mangocore.common.util.JsonBinder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by notreami on 17/10/29.
 */
public class RestAccessDeniedHandler implements AccessDeniedHandler {

    private static final Logger LOGGER = LogManager.getLogger(RestAccessDeniedHandler.class);

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {
        LOGGER.warn("Authentication Failed: " + accessDeniedException.getMessage());

        CommonResponse commonResponse = CommonResponse.createError(ErrorInfo.STATUS_INVALID);

        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        PrintWriter printWriter = response.getWriter();
        printWriter.write(JsonBinder.toJSONString(commonResponse));
        printWriter.flush();
        printWriter.close();
    }
}

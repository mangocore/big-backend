package com.mangocore.api;

import com.mangocore.api.response.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.TextStyle;
import java.util.Locale;

/**
 * 未授权认证测试接口
 * Created by notreami on 17/9/8.
 */
@Slf4j
@RestController
@RequestMapping("/test")
public class UnAuthTestController {
    private static final String activeStatus = "当前环境：%s，服务器时区：%s";
    @Value("${spring.profiles.active}")
    private String projectEnv;

    @GetMapping("/status/active")
    public CommonResponse activeStatus() {
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        ZoneId zoneId = zonedDateTime.getZone();
        String datetime = zoneId.getDisplayName(TextStyle.FULL, Locale.ROOT)
                + "(" + zoneId.getDisplayName(TextStyle.SHORT, Locale.ROOT) + ")  " + zonedDateTime;

        return CommonResponse.createSuccess(String.format(activeStatus, projectEnv, datetime));
    }
}

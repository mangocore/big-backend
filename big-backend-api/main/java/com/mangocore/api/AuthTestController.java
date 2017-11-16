package com.mangocore.api;

import com.mangocore.common.response.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 授权认证测试接口
 * Created by notreami on 17/9/8.
 */
@Slf4j
@RestController
@RequestMapping("/api/test")
@PreAuthorize("hasAuthority('AUTH_WRITE')")
public class AuthTestController {

    @PostMapping("/login")
    public CommonResponse login() {
        return CommonResponse.createSuccess(null);
    }

    @GetMapping("/hello")
    public CommonResponse hello() {
        return CommonResponse.createSuccess(null);
    }

    @GetMapping("/world")
    @PreAuthorize("hasRole('ADMIN')")
    public CommonResponse world() {
        return CommonResponse.createSuccess(null);
    }

}

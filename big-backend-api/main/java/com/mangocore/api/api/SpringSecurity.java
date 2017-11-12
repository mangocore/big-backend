package com.mangocore.api.api;

import com.mangocore.common.response.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by notreami on 17/10/29.
 */
@Slf4j
@RestController
@RequestMapping("/api")
@PreAuthorize("hasAuthority('AUTH_WRITE')")
public class SpringSecurity {

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

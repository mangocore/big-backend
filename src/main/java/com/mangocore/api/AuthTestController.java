package com.mangocore.api;

import com.mangocore.common.common.CommonOutput;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation(value = "测试登陆接口", response = CommonOutput.class)
    @PostMapping("/login")
    public CommonOutput login() {
        return CommonOutput.createSuccess(null);
    }

    @GetMapping("/hello")
    public CommonOutput hello() {
        return CommonOutput.createSuccess(null);
    }

    @GetMapping("/world")
    @PreAuthorize("hasRole('ADMIN')")
    public CommonOutput world() {
        return CommonOutput.createSuccess(null);
    }

}

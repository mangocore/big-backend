package com.mangocore.api.ucenter;

import com.mangocore.common.response.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户注册或登陆
 * Created by notreami on 17/11/13.
 */
@Slf4j
@RestController
@RequestMapping("/api/ucenter")
public class UserLoginController {

    @PostMapping("/login")
    public CommonResponse login() {
        return CommonResponse.createSuccess(null);
    }
}

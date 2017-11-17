package com.mangocore.common.common;

import lombok.Data;

/**
 * 调用方的信息
 * Created by notreami on 17/9/9.
 */
@Data
public class ClientInfo {
    /**
     * 请求的客户端平台
     */
    private Platform platform;

    /**
     * 用户登录token
     */
    private String token;

    /**
     * 用户请求的IP
     */
    private String userIp;

    /**
     * 用户请求的定位城市
     */
    private Integer cityId;


    public enum Platform {
        android(1, "android"),
        iphone(2, "iphone"),
        WP(3, "wp"),
        wap(4, "wap"),
        pc(5, "pc"),
        weChat(6, "weChat");

        private int code;
        private String desc;

        Platform(int code, String desc) {
            this.code = code;
            this.desc = desc;
        }
    }
}

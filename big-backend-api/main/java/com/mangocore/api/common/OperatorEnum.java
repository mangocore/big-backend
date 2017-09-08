package com.mangocore.api.common;

import lombok.Getter;

/**
 * Created by notreami on 17/9/9.
 */
@Getter
public enum OperatorEnum {
    /**
     * 用户
     */
    USER,

    /**
     * 客服
     */
    CS,

    /**
     * 商家
     */
    PARTNER,

    /**
     * 直连商家
     */
    DIRECT_PARTNER;
}

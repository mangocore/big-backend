package com.mangocore.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by notreami on 17/11/23.
 */
@Getter
@AllArgsConstructor
public enum OperatorType {
    SYSTEM(0), // 系统
    CUSTOMER_SERVICE(1), // 客服
    MERCHANTS(2), // 商家
    CUSTOMER(3); // 用户

    private int code;
}

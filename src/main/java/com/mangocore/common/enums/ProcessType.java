package com.mangocore.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by notreami on 17/11/22.
 */
@Getter
@AllArgsConstructor
public enum ProcessType {
    //操作流程
    CREATE_ACCOUNT(0, false, Group.CUSTOMER, "创建账户"),
    APPLY_ACCESS_TOKEN(1, false, Group.CUSTOMER, "申请访问token"),
    FREE_ACCESS_TOKEN(2, false, Group.CUSTOMER, "释放访问token");


    private Integer code;
    private boolean isFlow;//如何区分flow 类型
    private Group group;
    private String description;

    @Getter
    @AllArgsConstructor
    public enum Group {
        SYSTEM(0), // 系统
        CUSTOMER_SERVICE(1), // 客服
        MERCHANTS(2), // 商家
        CUSTOMER(3); // 用户

        private Integer code;
    }
}

package com.mangocore.common.common;

import lombok.Data;

/**
 * Created by notreami on 17/9/9.
 */
@Data
public class BaseInfo {
    /**
     * 唯一值，用于去除重复提交
     */
    private String uuId;

    /**
     * 用户id
     */
    private String userId;
}

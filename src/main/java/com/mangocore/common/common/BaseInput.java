package com.mangocore.common.common;

/**
 * Created by notreami on 17/11/23.
 */
public interface BaseInput {

    /**
     * 操作流水
     */
    String getTraceId();

    /**
     * 输入校验
     *
     * @return
     */
    default boolean isValid() {
        return true;
    }
}
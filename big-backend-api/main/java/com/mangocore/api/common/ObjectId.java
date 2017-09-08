package com.mangocore.api.common;

import lombok.Data;

/**
 * Created by notreami on 17/9/9.
 */
@Data
public class ObjectId {
    /**
     * 唯一值，用于去除重复提交
     */
    private String uuid;
}

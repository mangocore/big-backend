package com.mangocore.data.database.domain;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;

/**
 * Created by notreami on 17/6/24.
 */
@Data
public class SimpleDomain {
    private long id;
    private Date createtime;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss.SSS")
    private Date updatetime;
}

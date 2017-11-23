package com.mangocore.common.common;

import com.mangocore.common.enums.OperatorType;
import com.mangocore.common.enums.ProcessType;
import com.mangocore.common.util.JsonBinder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by notreami on 17/11/23.
 */
@Slf4j
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommonInput<T extends BaseInput> {

    /**
     * 调用方信息
     */
    private ClientInfo clientInfo;
    /**
     * 订单操作类型
     */
    private ProcessType processType;
    /**
     * 操作人类型
     */
    private OperatorType operatorType;

    /**
     * 操作人
     * 用户:userId
     * 商家: 登录用户名/账号/联系人 等
     * 客服: 客服编号
     */
    private String operator;

    /**
     * 泛型数据ClassName
     */
    private String dataClassName;

    /**
     * 泛型数据JSON串
     */
    private String dataJsonValue;
    /**
     * 操作对应的数据
     */
    private T data;


    public T getData() {
        if (data != null) {
            return data;
        }

        if (StringUtils.isBlank(dataJsonValue) || StringUtils.isBlank(dataClassName)) {
            return null;
        }

        try {
            return (T) JsonBinder.parseObject(dataJsonValue, Class.forName(dataClassName));
        } catch (Exception e) {
            log.error("数据转换异常, clazz={}, value={}", dataClassName, dataJsonValue, e);
            return null;
        }
    }

    public void setData(T data) {
        this.data = data;
        if (data != null) {
            setDataClassName(data.getClass().getName());
            setDataJsonValue(JsonBinder.toJSONString(data));
        }
    }


}

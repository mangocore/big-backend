package com.mangocore.api.response;

import com.google.errorprone.annotations.Var;
import com.mangocore.api.common.ErrorInfo;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

/**
 * Created by notreami on 17/9/8.
 */
@Data
@Builder
public class CommonResponse<T> {
    /**
     * 操作结果
     */
    private boolean success;

    /**
     * 操作错误类型
     */
    private ErrorInfo errorInfo;

    /**
     * 具体业务返回数据
     */
    private T data;

    @Tolerate
    public CommonResponse() {
    }

    /**
     * 逻辑返回是否成功
     *
     * @return
     */
    public boolean success() {
        return success && errorInfo == null;
    }

    public static <K> CommonResponse<K> createSuccess(K resp) {
        CommonResponse<K> response = new CommonResponse<K>();
        response.setSuccess(true);
        response.setData(resp);
        return response;
    }

    public static <K> CommonResponse<K> createError(ErrorInfo error) {
        CommonResponse<K> response = new CommonResponse<K>();
        response.setErrorInfo(error);
        response.setSuccess(false);
        response.setData(null);
        return response;
    }
}

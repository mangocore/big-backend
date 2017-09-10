package com.mangocore.common.common;


import lombok.Data;

import static com.mangocore.common.common.ErrorInfo.ErrorCode.*;

/**
 * Created by hanyabin on 17/7/20.
 */
@Data
public class ErrorInfo {
    public static final ErrorInfo PARAM_INVALID = new ErrorInfo(PARAM_ERROR, "参数异常");
    public static final ErrorInfo PROCESS_INVALID = new ErrorInfo(PROCESS_ERROR, "处理异常");
    public static final ErrorInfo DEPT_INVALID = new ErrorInfo(DEPT_ERROR, "依赖异常");
    public static final ErrorInfo STATUS_INVALID = new ErrorInfo(STATUS_ERROR, "状态不合法");
    public static final ErrorInfo SYS_INVALID = new ErrorInfo(SYS_ERROR, "系统未知异常");


    //下单返回
    public static final ErrorInfo PARAM_INVALID_CONTACT = new ErrorInfo(PARAM_ERROR_CONTACT, "联系人信息出错");
    public static final ErrorInfo PROCESS_INVALID_OFFLINE = new ErrorInfo(PROCESS_ERROR_OFFLINE, "产品已经下线");
    public static final ErrorInfo PROCESS_INVALID_EXCEED_STOCK = new ErrorInfo(PROCESS_ERROR_EXCEED_STOCK, "可购买库存不足");
    public static final ErrorInfo PROCESS_INVALID_ACTIVE_EXCEED_STOCK = new ErrorInfo(PROCESS_ERROR_ACTIVE_EXCEED_STOCK, "可购买活动库存不足");
    public static final ErrorInfo PROCESS_INVALID_RISK = new ErrorInfo(PROCESS_ERROR_RISK, "风控校验失败");
    public static final ErrorInfo PROCESS_INVALID_LOCK_STOCK = new ErrorInfo(PROCESS_ERROR_LOCK_STOCK, "锁定库存失败");
    public static final ErrorInfo PROCESS_INVALID_MONEY_CHANGED = new ErrorInfo(PROCESS_ERROR_MONEY_CHANGED, "产品金额发生变动");

    private int code;
    private String message;



    public ErrorInfo() {
    }

    public ErrorInfo(int code, String message) {
        this.code = code;
        this.message = message;
    }


    public static ErrorInfo error(int code, String message) {
        return new ErrorInfo(code, message);
    }

    public static final class ErrorCode {
        public static final int PARAM_ERROR = 100; // 参数异常
        public static final int PARAM_ERROR_CONTACT = 101; // 参数异常(联系人信息出错)
        public static final int PROCESS_ERROR = 200; // 处理异常
        public static final int PROCESS_ERROR_OFFLINE = 201; // 处理异常（产品已经下线）
        public static final int PROCESS_ERROR_EXCEED_STOCK = 202; // 处理异常（可购买库存不足）
        public static final int PROCESS_ERROR_ACTIVE_EXCEED_STOCK = 203; // 处理异常（可购买活动库存不足）
        public static final int PROCESS_ERROR_RISK = 204; // 处理异常（风控校验失败）
        public static final int PROCESS_ERROR_LOCK_STOCK = 205; // 处理异常（锁定库存失败）
        public static final int PROCESS_ERROR_MONEY_CHANGED = 206; // 处理异常（产品金额发生变动）
        public static final int DEPT_ERROR = 300; // 依赖异常
        public static final int STATUS_ERROR = 400; // 订单状态不合法
        public static final int SYS_ERROR = 500; // 系统未知异常

    }
}

package com.mangocore.common.common;

import lombok.Getter;

/**
 * Created by notreami on 17/9/9.
 */
@Getter
public enum ProcessType {
    SUBMIT_ORDER(1, "提交订单"),
    SUBMIT_ORDER_ASYNC(2, "异步提交订单"),
    APPLY_SUBMIT_RESULT(4, "订单提交结果"),//用于异步下单
    APPLY_PAYTOKEN(8, "申请支付Token"),
    CLOSE_ORDER(16, "订单关闭"),
    //    PAYED_ORDER(32, "订单支付成功"),
    CONFIRM_ORDER(64, "订单确认"), // 包括创建成功和失败
    CONSUMED_ORDER(128, "订单消费"),
    REFUND_ORDER(256, "订单退款"),
    //    REFUNDED_ORDER(512, "订单退款成功"),
    CANCEL_ORDER(1024, "订单取消");

    private int code;
    private String desc;

    ProcessType(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}

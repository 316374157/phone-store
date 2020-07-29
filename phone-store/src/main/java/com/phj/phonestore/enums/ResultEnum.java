package com.phj.phonestore.enums;

import lombok.Getter;

/**
 * @author 31637
 * @date 2020/7/25 11:05
 */
@Getter
public enum  ResultEnum {
    /**
     * 手机库存不足错误
     */
    PHONE_STOCK_ERROR(0,"手机库存不足"),
    ORDER_NOT_EXIST(1,"订单不存在");

    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}

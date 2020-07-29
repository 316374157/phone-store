package com.phj.phonestore.enums;

import lombok.Getter;

/**
 * @author 31637
 * @date 2020/7/25 18:31
 */
@Getter
public enum PayStatus {
    /**
     * 手机库存不足错误
     */
    UNPIAD(0,"未支付"),
    PIAD(1,"已支付");

    private Integer code;
    private String msg;

    PayStatus(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}

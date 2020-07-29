package com.phj.phonestore.exception;

import com.phj.phonestore.enums.ResultEnum;

/**
 * @author 31637
 * @date 2020/7/25 10:52
 */
public class PhoneException extends RuntimeException {

    public PhoneException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
    }

    public PhoneException(String error) {
        super(error);
    }
}

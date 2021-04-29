package com.onestop.common.core.exception;

/**
 * 支付异常类
 * @author Clark
 * @version 2020/07/22
 */
public class PayException extends BaseException {
    public PayException(String msg) {
        super(msg);
    }

    public PayException(int code, String msg) {
        super(code, msg);
    }
}
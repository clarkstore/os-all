package com.onestop.common.core.exception;

/**
 * 支付异常类
 * @author Clark
 * @version 2020/07/22
 */
public class OsPayException extends OsBaseException {
    public OsPayException(String msg) {
        super(msg);
    }

    public OsPayException(int code, String msg) {
        super(code, msg);
    }
}
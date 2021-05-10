package com.onestop.common.core.exception;

/**
 * 限流异常
 * @author Clark
 * @version 2021/5/10
 */
public class OsAccessLimitException extends OsBaseException {
    public OsAccessLimitException(String msg) {
        super(msg);
    }

    public OsAccessLimitException(int code, String msg) {
        super(code, msg);
    }
}

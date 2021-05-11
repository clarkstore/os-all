package com.onestop.common.core.exception;

/**
 * 限流异常
 * @author Clark
 * @version 2021/5/10
 */
public class OsAccessLimitException extends OsBaseException {
    private static final String ERR_MSG = "访问超出频率限制，请稍后再试!";
    public OsAccessLimitException() {
        super(ERR_MSG);
    }

    public OsAccessLimitException(String msg) {
        super(msg);
    }

    public OsAccessLimitException(int code, String msg) {
        super(code, msg);
    }
}

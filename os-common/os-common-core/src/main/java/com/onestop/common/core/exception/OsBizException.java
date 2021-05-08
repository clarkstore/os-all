package com.onestop.common.core.exception;

/**
 * 业务异常类
 * @author Clark
 * @version 2020/04/07
 */
public class OsBizException extends OsBaseException {
    public OsBizException(String msg) {
        super(msg);
    }

    public OsBizException(int code, String msg) {
        super(code, msg);
    }
}

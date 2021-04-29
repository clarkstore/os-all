package com.onestop.common.core.exception;

/**
 * 业务异常类
 * @author Clark
 * @version 2020/04/07
 */
public class BizException extends BaseException {
    public BizException(String msg) {
        super(msg);
    }

    public BizException(int code, String msg) {
        super(code, msg);
    }
}

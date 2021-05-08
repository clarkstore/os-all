package com.onestop.common.core.exception;

import lombok.*;

/**
 * 自定义异常基类
 * @author Clark
 * @version 2021/04/29
 */
@Getter
@Setter
public abstract class OsBaseException extends RuntimeException {
    /**
     * 异常编号
     */
    protected int code;
    /**
     * 异常内容
     */
    protected String msg;

    public OsBaseException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public OsBaseException(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }
}

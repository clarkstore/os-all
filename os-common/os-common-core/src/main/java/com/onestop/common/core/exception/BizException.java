package com.onestop.common.core.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 业务异常类
 * @author Clark
 * @version 2020/04/07
 */
@Data
@AllArgsConstructor
public class BizException extends RuntimeException {
    /**
     * 异常编号
     */
    protected String code;
    /**
     * 异常内容
     */
    protected String msg;
}

package com.onestop.common.core.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 支付异常类
 * @author Clark
 * @version 2020/07/22
 */
@Data
@AllArgsConstructor
public class PayException extends RuntimeException {
    /**
     * 异常编号
     */
    protected String code;
    /**
     * 异常内容
     */
    protected String msg;
}
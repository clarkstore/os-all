package com.onestop.common.core.exception;

import com.onestop.common.core.util.Res;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理
 * 新建异常已处理类时，通过@Order定义扩展顺序
 * @author Clark
 * @version 2020/08/06
 */
@Slf4j
@RestControllerAdvice
@Order(Ordered.LOWEST_PRECEDENCE)
public class OsGlobalExceptionHandler {
    /**
     *
     * @param e 业务异常
     * @return Res 返回结果
     */
    @ExceptionHandler(value = {OsBizException.class})
    public Res bizExceptionHandle(OsBizException e) {
        log.error("=========业务异常=========");
        log.error(e.getMsg());
        return Res.failed(e.getMsg());
    }

    /**
     *
     * @param e 支付异常
     * @return Res 返回结果
     */
    @ExceptionHandler(value = {OsPayException.class})
    public Res payExceptionHandle(OsPayException e) {
        log.error("=========支付异常=========");
        log.error(e.getMsg());
        return Res.failed(e.getMsg());
    }

    /**
     *
     * @param e 未知异常
     * @return Res 返回结果
     */
    @ExceptionHandler(value = {Exception.class})
    public Res exceptionHandle(Exception e) {
        log.error("=========未知异常=========");
        log.error(e.getMessage());
        e.printStackTrace();
        return Res.restResult(Res.MsgCode.FAIL, e.getMessage(), null);
    }
}

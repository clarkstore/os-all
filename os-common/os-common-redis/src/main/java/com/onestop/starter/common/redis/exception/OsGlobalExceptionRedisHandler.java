package com.onestop.starter.common.redis.exception;

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
 * @version 2021/05/17
 */
@Slf4j
@RestControllerAdvice
@Order(Ordered.LOWEST_PRECEDENCE - 1)
public class OsGlobalExceptionRedisHandler {
    /**
     *
     * @param e 限流异常
     * @return Res 返回结果
     */
    @ExceptionHandler(value = {OsAccessLimitException.class})
    public Res accessLimitExceptionHandle(OsAccessLimitException e) {
        log.error("=========限流异常=========");
        log.error(e.getMsg());
        return Res.failed(e.getMsg());
    }
}

package com.onestop.common.core.exception;

import com.onestop.common.core.util.Res;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 全局异常处理
 *
 * @author Clark
 * @version 2020/08/06
 */
@Slf4j
public class OsGlobalExceptionHandler {
    /**
     *
     * @param e Biz Error
     * @return
     */
    @ExceptionHandler(value = {BizException.class})
    public Res bizExceptionHandle(BizException e) {
        log.error("=========业务异常=========");
        log.error(e.getMsg());
        return Res.failed(e.getMsg());
    }

    /**
     *
     * @param e Pay Error
     * @return
     */
    @ExceptionHandler(value = {PayException.class})
    public Res payExceptionHandle(PayException e) {
        log.error("=========支付异常=========");
        log.error(e.getMsg());
        return Res.failed(e.getMsg());
    }

    /**
     *
     * @param e 未知异常
     * @return
     */
    @ExceptionHandler(value = {Exception.class})
    public Res exceptionHandle(Exception e) {
        log.error("=========未知异常=========");
        log.error(e.getMessage());
        e.printStackTrace();
        return Res.restResult(Res.MsgCode.FAIL, e.getMessage(), null);
    }
}

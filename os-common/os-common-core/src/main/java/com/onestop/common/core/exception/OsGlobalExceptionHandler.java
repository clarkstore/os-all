/*
 *
 *  * Copyright (c) 2021 os-parent Authors. All Rights Reserved.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *     http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

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
 * @version 2021/05/17
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

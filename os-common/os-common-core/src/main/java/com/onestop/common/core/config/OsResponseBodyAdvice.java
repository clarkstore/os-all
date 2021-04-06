package com.onestop.common.core.config;

import com.onestop.common.core.util.Res;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 实现统一处理controller返回
 *
 * @author Clark
 * @version 2020-04-06
 */
//@RestControllerAdvice("com.onestop")
public abstract class OsResponseBodyAdvice implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType
            , Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (body instanceof Res) {
            Res res = (Res) body;
//            TODO 重写统一返回处理
            return res;
        }

        return body;
    }
}
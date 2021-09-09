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

package com.onestop.common.web.interceptor;

import cn.hutool.core.util.StrUtil;
import com.onestop.common.core.exception.OsBizException;
import com.onestop.common.web.annotation.OsAuthTokenAnnotation;
import com.onestop.common.web.constant.OsWebConsts;
import com.onestop.common.web.util.OsTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 自定义token拦截器
 *
 * @author Clark
 * @version 2021-04-02
 */
@Configuration
@ConditionalOnBean(OsTokenUtils.class)
public class OsTokenInterceptor implements HandlerInterceptor {
    @Autowired
    protected OsTokenUtils tokenUtils;

    /**
     * 统一验证token
     *
     * @param request  request
     * @param response response
     * @param handler  handler
     * @return boolean
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        //检查是否有AuthToken注释，有则认证
        if (method.isAnnotationPresent(OsAuthTokenAnnotation.class)) {
            OsAuthTokenAnnotation authToken = method.getAnnotation(OsAuthTokenAnnotation.class);
            if (authToken.required()) {
                String token = request.getHeader(OsWebConsts.HEADER_TOKEN);
                if (StrUtil.isBlank(token) || !this.tokenUtils.verify(token)) {
                    throw new OsBizException("token验证失败");
                }
                // TODO 重写时追加自定义token逻辑
//                if (StrUtil.isNotBlank(token) && StrUtil.isNotBlank(userid)) {
//                    // 验证token
//                    if (!TokenUtils.verify(token, userid)) {
//                        // 会话验证失败
//                        this.returnJson(response);
//                        return false;
//                    }
//                }
            }
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    }
}
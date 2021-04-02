package com.onestop.common.core.interceptor;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.onestop.common.core.annotation.AuthToken;
import com.onestop.common.core.constant.OsCoreConsts;
import com.onestop.common.core.exception.BizException;
import com.onestop.common.core.util.OsTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * 自定义token拦截器
 *
 * @author Clark
 * @version 2021-04-02
 */
@Configuration
public class OsTokenInterceptor implements HandlerInterceptor {
    @Autowired
    protected OsTokenUtils tokenUtils;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
//        if (request.getMethod().equals("OPTIONS")) {
//            response.setStatus(HttpServletResponse.SC_OK);
//            return true;
//        }
        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        //检查是否有AuthToken注释，有则认证
        if (method.isAnnotationPresent(AuthToken.class)) {
            AuthToken authToken = method.getAnnotation(AuthToken.class);
            if (authToken.required()) {
                String token = request.getHeader(OsCoreConsts.HEADER_TOKEN);
                if (StrUtil.isNotBlank(token)) {
                    if (!this.tokenUtils.verify(token)) {
                        throw new BizException("", "token验证失败");
                    }
                }

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

    /**
     * 返回token失败消息
     *
     * @param response
     * @throws Exception
     */
    private void returnJson(HttpServletResponse response) {
        Map<String, Object> map = CollUtil.newHashMap();
        map.put("title", "验证失败");
        map.put("description", "请重新登录");

        String json = JSONUtil.toJsonPrettyStr(map);
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        try {
            writer = response.getWriter();
            writer.print(json);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    }
}
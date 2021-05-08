package com.onestop.common.core.interceptor;

import com.onestop.common.core.annotation.OsAccessLimit;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 限流拦截器
 * @author Clark
 * @version 2021/5/8
 */

@Slf4j
@Configuration
public class OsAccessLimitInterceptor implements HandlerInterceptor {

    @Autowired
    private RedissonClient redissonClient;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler)
            throws Exception {
        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        //判断该方法上是否有自定义注解@AccessLimit
        if (method.isAnnotationPresent(OsAccessLimit.class)) {
            OsAccessLimit accessLimit = method.getAnnotation(OsAccessLimit.class);
//获取注解属性值
            long limit = accessLimit.count();
            long sec = accessLimit.sec();
            //可将用户的id加上  限制每一个用户只能访问几次
            String key = request.getRequestURI();
            log.info("key : {}", key);
            //从redis中获取记录
//            redissonClient
            long maxLimit = redissonClient.getAtomicLong(key).get();
//            if (maxLimit == null) {
//                //第一次，计数器设置为1，设置redis过期时间
//                redisTemplate.opsForValue().set(key, 1, sec, TimeUnit.SECONDS);
//            } else if (maxLimit < limit) {
//                //计数器加1
//                redisTemplate.opsForValue().set(key, maxLimit + 1, sec, TimeUnit.SECONDS);
//            } else {
//                throw new AccessLimitException("过度访问资源,客官休息一会哦！");
//            }
        }

        return true;
    }
}
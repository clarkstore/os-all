package com.onestop.starter.common.redis.config;

import com.onestop.starter.common.redis.interceptor.OsAccessLimitInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * WEB初始化配置基类
 * 可以继承实现拦截器重写等
 *
 * @author Clark
 * @version 2020-04-06
 */
@Configuration
public class OsRedisWebConfig implements WebMvcConfigurer {
    // TODO 可以继承自定义限流拦截器
    @Autowired(required = false)
    private OsAccessLimitInterceptor accessLimitInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        if (this.accessLimitInterceptor != null) {
            registry.addInterceptor(this.accessLimitInterceptor)
                    .addPathPatterns("/**");
        //  .excludePathPatterns("/不被拦截路径 通常为登录注册或者首页");
        }
    }

    /**
     * 跨域支持
     *
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedHeaders("Access-Control-Allow-Origin:*")
                .allowedMethods("*")
                .allowedOrigins("*");
    }
}

package com.onestop.common.core.config;

import com.onestop.common.core.interceptor.OsTokenInterceptor;
import com.onestop.common.core.util.OsTokenUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
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
@Order(Ordered.LOWEST_PRECEDENCE)
public class OsWebConfig implements WebMvcConfigurer {
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
// TODO 根据业务重写以下方法
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        OsTokenInterceptor tokenInterceptor = this.tokenInterceptor();
        if (tokenInterceptor != null) {
            registry.addInterceptor(this.tokenInterceptor())
                    .addPathPatterns("/**");
        }
    }

// TODO 重写OsTokenInterceptor时，使用新类创建对象
    @Bean
    @ConditionalOnBean(OsTokenUtils.class)
    public OsTokenInterceptor tokenInterceptor() {
        return new OsTokenInterceptor();
    }

// TODO 是否需要对前端通过header传递参数，进行统一处理

//    @ModelAttribute(name = "openid")
//    public String getHeaderOpenid() {
//        return request.getHeader(WxConsts.HEADER_OPENID);
//    }
}
package com.onestop.common.core.config;

import com.onestop.common.core.interceptor.OsTokenInterceptor;
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
public class OsWebConfig implements WebMvcConfigurer {
    // TODO 可以继承自定义Token拦截器
    @Autowired
    protected OsTokenInterceptor tokenInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        if (this.tokenInterceptor != null) {
            registry.addInterceptor(this.tokenInterceptor)
                    .addPathPatterns("/**");
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
// TODO 根据业务重写以下方法

//    @Override
//    @ConditionalOnProperty(prefix = "os.token", name = "isopen", havingValue = "true")
//    public void addInterceptors(InterceptorRegistry registry) {
//        public void addInterceptors(InterceptorRegistry registry) {
//            if (this.tokenInterceptor != null) {
//                registry.addInterceptor(this.tokenInterceptor)
//                        .addPathPatterns("/**");
//            }
//        }
//    }

// TODO 是否需要对前端通过header传递参数，进行统一处理

//    @ModelAttribute(name = "openid")
//    public String getHeaderOpenid() {
//        return request.getHeader(WxConsts.HEADER_OPENID);
//    }
}

package com.onestop.starter.common.core.config;

import com.onestop.common.core.interceptor.OsTokenInterceptor;
import com.onestop.common.core.util.OsTokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Token配置
 * @author Clark
 * @version 2021-02-24
 */
@Configuration
@EnableConfigurationProperties(TokenProperties.class)
@ConditionalOnProperty(prefix = "os.token", name = "isopen", havingValue = "true")
public class OsCoreTokenAutoConfiguration {

    @Autowired
    private TokenProperties properties;

    @Bean
    @ConditionalOnMissingBean
    public OsTokenUtils osTokenUtils() {
        OsTokenUtils utils = new OsTokenUtils(this.properties.getSecret(), this.properties.getExpireTimeInMinutes(), this.properties.getClaimKey());
        return utils;
    }
}

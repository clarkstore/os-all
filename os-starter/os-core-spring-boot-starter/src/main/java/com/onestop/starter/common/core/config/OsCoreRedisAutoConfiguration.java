package com.onestop.starter.common.core.config;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Token配置
 * @author Clark
 * @version 2021-02-24
 */
//@Configuration
//@EnableConfigurationProperties(RedisProperties.class)
//@ConditionalOnProperty(prefix = "os.redis", name = "enabled", havingValue = "true")
public class OsCoreRedisAutoConfiguration {

    @Autowired
    private RedisProperties properties;

//    @Bean
//    @ConditionalOnMissingBean
//    public OsTokenUtils build() {
//        OsTokenUtils utils = new OsTokenUtils(this.properties.getSecret(), this.properties.getExpireTime(), this.properties.getClaimKey());
//        return utils;
//    }
}

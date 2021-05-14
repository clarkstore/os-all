package com.onestop.starter.common.mybatis.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * os-common-core配置
 * @author Clark
 * @version 2021-02-24
 */
@Configuration
@Import({ OsMybatisAutoConfiguration.class })
public class OsStarterMybatisAutoConfiguration {
}

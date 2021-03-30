package com.onestop.starter.common.azure.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * os-common-azure配置
 * @author Clark
 * @version 2021-03-30
 */
@Configuration
@Import({ OsAzureStorageAutoConfiguration.class })
public class OsAzureAutoConfiguration {
}

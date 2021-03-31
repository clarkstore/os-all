package com.onestop.starter.common.azure.config;

import com.onestop.common.azure.storage.util.OsAzureStorageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Storage配置
 *
 * @author Clark
 * @version 2021-03-30
 */
@Configuration
@EnableConfigurationProperties(StorageProperties.class)
@ConditionalOnProperty(prefix = "os.azure.storage", name = "isopen", havingValue = "true")
public class OsAzureStorageAutoConfiguration {

    @Autowired
    private StorageProperties properties;

    @Bean
    @ConditionalOnMissingBean
    public OsAzureStorageUtils osAzureStorageUtils() {
        OsAzureStorageUtils storageUtils = new OsAzureStorageUtils(this.properties.getConnectionString(), this.properties.getContainerName(), this.properties.getExpireTimeInMinutes());
        return storageUtils;
    }
}

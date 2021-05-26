/*
 *
 *  * Copyright (c) 2021 os-parent Authors. All Rights Reserved.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *     http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

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
@EnableConfigurationProperties(OsStorageProperties.class)
@ConditionalOnProperty(prefix = "os.azure.storage", name = "enabled", havingValue = "true")
public class OsAzureStorageAutoConfiguration {

    @Autowired
    private OsStorageProperties properties;

    @Bean
    @ConditionalOnMissingBean
    public OsAzureStorageUtils osAzureStorageUtils() {
        OsAzureStorageUtils storageUtils = new OsAzureStorageUtils(this.properties.getConnectionString(), this.properties.getContainerName(), this.properties.getExpireTimeInMinutes());
        return storageUtils;
    }
}

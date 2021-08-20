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

package com.onestop.starter.common.core.autoconfigure;

import cn.hutool.extra.mail.MailAccount;
import com.onestop.common.core.util.OsMailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 邮件配置
 * @author Clark
 * @version 2021-02-24
 */
@Configuration
@EnableConfigurationProperties(OsMailProperties.class)
@ConditionalOnProperty(prefix = "os.mail", name = "enabled", havingValue = "true")
public class OsMailAutoConfiguration {
    @Autowired
    private OsMailProperties properties;

    @Bean
    @ConditionalOnMissingBean
    public OsMailUtils osMailUtils() {
        MailAccount account = new MailAccount();
        account.setHost(this.properties.getHost());
        account.setPort(this.properties.getPort());
        account.setFrom(this.properties.getFrom());
        account.setUser(this.properties.getUser());
        account.setPass(this.properties.getPass());
        account.setStarttlsEnable(this.properties.isStarttlsEnable());
        account.setSslEnable(this.properties.isSslEnable());

        OsMailUtils mailUtils = new OsMailUtils();
        mailUtils.setMailAccount(account);
        return mailUtils;
    }
}
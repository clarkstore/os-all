package com.onestop.starter.common.core.config;

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
public class OsCoreMailAutoConfiguration {
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

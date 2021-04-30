package com.onestop.starter.common.task.config;

import com.onestop.common.task.util.OsTaskSchedulerUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * os-common-task配置
 * @author Clark
 * @version 2021-04-08
 */
@Configuration
@ConditionalOnProperty(prefix = "os.task", name = "enabled", havingValue = "true")
public class OsTaskSchedulerAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public ThreadPoolTaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        return taskScheduler;
    }

    @Bean
    @ConditionalOnBean(ThreadPoolTaskScheduler.class)
    public OsTaskSchedulerUtils osTaskSchedulerUtils() {
        OsTaskSchedulerUtils schedulerUtils = new OsTaskSchedulerUtils();
        return schedulerUtils;
    }
}

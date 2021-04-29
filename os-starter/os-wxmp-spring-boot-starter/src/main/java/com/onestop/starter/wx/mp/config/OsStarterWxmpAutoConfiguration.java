package com.onestop.starter.wx.mp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * os-wx-mp配置
 *
 * @author Clark
 * @version 2021-04-29
 */
@Configuration
@Import({OsWxmpAutoConfiguration.class, OsWxmpMenuConfiguration.class})
public class OsStarterWxmpAutoConfiguration {
}

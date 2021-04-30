package com.onestop.starter.wx.mini.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * os-wx-mini配置
 * @author Clark
 * @version 2021-03-18
 */
@Configuration
@Import({ OsStarterWxminiAutoConfiguration.class, OsWxminiSubscribeAutoConfiguration.class })
public class OsStarterWxminiAutoConfiguration {
}

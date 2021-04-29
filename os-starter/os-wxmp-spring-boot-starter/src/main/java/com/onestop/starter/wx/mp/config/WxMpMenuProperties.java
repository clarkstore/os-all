package com.onestop.starter.wx.mp.config;

import com.onestop.wx.mp.model.dto.MenuDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * 菜单配置
 *
 * @author Clark
 * @version 2021-04-29
 */
@Getter
@Setter
@ToString
@ConfigurationProperties(prefix = "os.wxmp.menu")
public class WxMpMenuProperties {
    /**
     * 菜单配置
     */
    private List<MenuDto> configs;
}

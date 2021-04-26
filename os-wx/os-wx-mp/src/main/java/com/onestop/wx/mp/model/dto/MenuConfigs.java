package com.onestop.wx.mp.model.dto;

import com.onestop.wx.mp.model.dto.MenuDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * 菜单信息
 *
 * @author Clark
 * @version 2021/04/23
 */
@Getter
@Setter
@ToString
public class MenuConfigs {
  /**
     * 菜单配置
     */
    private List<MenuDto> configs;
}

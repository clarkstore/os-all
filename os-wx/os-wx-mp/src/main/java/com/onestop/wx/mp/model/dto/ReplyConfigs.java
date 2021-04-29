package com.onestop.wx.mp.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * 关键字回复
 *
 * @author Clark
 * @version 2021/04/23
 */
@Getter
@Setter
@ToString
public class ReplyConfigs {
  /**
     * 菜单配置
     */
    private List<ReplyDto> configs;
}

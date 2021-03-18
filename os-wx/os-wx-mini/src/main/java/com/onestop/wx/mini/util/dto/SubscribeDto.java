package com.onestop.wx.mini.util.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Tolerate;

import java.util.List;

/**
 * 订阅消息类
 * @author Clark
 * @version 2021-03-18
 */
@Builder
@Getter
@Setter
@ToString
public class SubscribeDto {
    @Tolerate
    public SubscribeDto() {
    }
    /**
     * 订阅编号:业务调用自定义常量
     */
    private String msgId;
    /**
     * 订阅消息模板ID
     */
    private String tplId;
    /**
     * 订阅消息跳转页面
     */
    private String page;
    /**
     * 订阅消息参数名列表
     */
    private List<String> nameList;
}

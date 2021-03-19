package com.onestop.wx.mini.util.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * 订阅消息请求类
 * @author Clark
 * @version 2021-03-18
 */
@Getter
@Setter
@ToString
public class SubscribeReqDto {
    /**
     * 订阅编号
     */
    private String msgId;
    /**
     * openid
     */
    private String openid;
    /**
     * 订阅消息参数值列表
     */
    private List<String> valueList;
}

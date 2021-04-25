package com.onestop.wx.mp.extra.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * 用户信息
 *
 * @author Clark
 * @version 2020/08/06
 */
@Getter
@Setter
@ToString
public class WxmpUser {

    private String id;
    /**
     * openid
     */
    private String openid;
    /**
     * 是否关注
     */
    private String subscribe;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 关注时间
     */
    private LocalDateTime subscribeTime;
    /**
     * 取消关注时间
     */
    private LocalDateTime unsubscribeTime;
}

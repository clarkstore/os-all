package com.onestop.wx.mp.extra.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * 回复信息
 *
 * @author Clark
 * @version 2021/04/25
 */
@Getter
@Setter
@ToString
public class WxmpReply {

    private String id;
    /**
     * 规则名
     */
    private String name;
    /**
     * 回复类型 01：关注、02：关键字回复、03：客服消息
     */
    private String replyType;
    /**
     * 关键字
     */
    private String keyword;
    /**
     * 消息类型：文本、图片、图文、视频、音频
     */
    private String msgType;
    /**
     * 文本回复内容
     */
    private String replyText;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 删除标识
     */
    private String deleted;
}

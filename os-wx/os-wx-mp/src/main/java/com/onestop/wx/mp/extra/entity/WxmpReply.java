package com.onestop.wx.mp.extra.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * 回复信息
 *
 * @author Clark
 * @date 2020/08/06
 */
@Data
@ToString
@TableName("wxmp_reply")
public class WxmpReply extends Model<WxmpReply> {

    @TableId(value = "id")
    private String id;
    /**
     * 规则名
     */
    private String name;
    /**
     * 回复类型 01：关注、02：关键字回复、03：客服消息
     */
    @TableField("reply_type")
    private String replyType;
    /**
     * 关键字
     */
    private String keyword;
    /**
     * 消息类型：文本、图片、图文、视频、音频
     */
    @TableField("msg_type")
    private String msgType;
    /**
     * 文本回复内容
     */
    @TableField("reply_text")
    private String replyText;
    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    @TableField(value = "update_time", fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;

    /**
     * 删除标识
     */
    @TableField("deleted")
    private String deleted;
}

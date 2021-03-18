package com.onestop.wx.mp.extra.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * 用户信息
 *
 * @author Clark
 * @date 2020/08/06
 */
@Data
@ToString
@TableName("wxmp_user")
public class WxmpUser extends Model<WxmpUser> {

    @TableId(value = "id")
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
    @TableField("subscribe_time")
    private LocalDateTime subscribeTime;
    /**
     * 取消关注时间
     */
    @TableField("unsubscribe_time")
    private LocalDateTime unsubscribeTime;
}

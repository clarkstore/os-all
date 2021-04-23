package com.onestop.wx.mp.extra.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.ToString;

/**
 * 菜单信息
 *
 * @author Clark
 * @version 2020/08/06
 */
@Data
@ToString
//@TableName("wxmp_menu")
public class WxmpMenu extends Model<WxmpMenu> {

    @TableId(value = "id")
    private String id;
    /**
     * 父级菜单ID
     */
    @TableField("parent_id")
    private String parentId;
    /**
     * 菜单组
     */
    @TableField("menu_group")
    private String menuGroup;
    /**
     * 菜单的响应动作类型，view表示网页类型，click表示点击类型，miniprogram表示小程序类型
     */
    @TableField("menu_type")
    private String menuType;
    /**
     * 菜单标题
     */
    @TableField("menu_name")
    private String menuName;
    /**
     * 菜单key
     */
    @TableField("menu_key")
    private String menuKey;
    /**
     * url: view、miniprogram类型必须
     */
    private String url;
    /**
     * 小程序的appid
     */
    private String appid;
    /**
     * 小程序的页面路径
     */
    private String pagepath;
    /**
     * 菜单级别：1：一级、2：二级
     */
    @TableField("menu_level")
    private String menuLevel;
    /**
     * 排序
     */
    private String sort;
    /**
     * 删除标识
     */
    @TableField("deleted")
    private String deleted;
}

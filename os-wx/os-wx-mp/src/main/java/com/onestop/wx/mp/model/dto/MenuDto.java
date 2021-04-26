package com.onestop.wx.mp.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 菜单信息
 *
 * @author Clark
 * @version 2021/04/23
 */
@Getter
@Setter
@ToString
public class MenuDto {

    private String id;
    /**
     * 父级菜单ID
     */
    private String parentId;
    /**
     * 菜单组
     */
    private String menuGroup;
    /**
     * 菜单的响应动作类型，view表示网页类型，click表示点击类型，miniprogram表示小程序类型
     */
    private String menuType;
    /**
     * 菜单标题
     */
    private String menuName;
    /**
     * 菜单key
     */
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
    private String menuLevel;
    /**
     * 排序
     */
    private String sort;
    /**
     * 删除标识
     */
    private String deleted;
}

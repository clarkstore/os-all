package com.onestop.wx.mp.util;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.bean.menu.WxMenu;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;

/**
 * 微信服务号工具类
 *
 * @author Clark
 * @version 2020-09-03
 */
@Slf4j
@Component
public class OsWxMpUtils {
    @Autowired
    private WxMpService wxService;

    /**
     * 获取openid
     *
     * @param code
     * @return Openid
     */
    public String getOpenid(String code) {
        try {
            String openid = this.wxService.getOAuth2Service().getAccessToken(code).getOpenId();
            return openid;
        } catch (WxErrorException e) {
            log.error("==========获取openid异常==========");
            log.error(e.getMessage());
        }
        return null;
    }

    /**
     * 发送模板消息
     *
     * @param openid
     * @param templateId
     * @param url
     * @param args
     */
    public void sendTemplateMsg(String openid, String templateId, String url, Map<String, String> args) throws WxErrorException {
        WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder().toUser(openid).templateId(templateId).build();
        // 详情链接
        if (StrUtil.isNotBlank(url)) {
            templateMessage.setUrl(url);
        }
        // 填充参数
        args.forEach((k, v) -> templateMessage.addData(new WxMpTemplateData(k, v)));

        String msgId = this.wxService.getTemplateMsgService().sendTemplateMsg(templateMessage);
    }

    /**
     * 取得微信用户信息
     *
     * @param openid
     * @return WxmpUser
     */
    // public WxmpUser getWxUser(String openid) {
    //     WxmpUser condition = new WxmpUser();
    //     condition.setOpenid(openid);

    //     QueryWrapper<WxmpUser> queryWrapper = new QueryWrapper<>();
    //     queryWrapper.setEntity(condition);
    //     WxmpUser entity = condition.selectOne(queryWrapper);
    //     return entity;
    // }

    /**
     * 关键字回复
     *
     * @param keyword
     */
    public void keywordReply(String openid, String keyword) {
        // try {
        //     if (StrUtil.isBlank(openid) || StrUtil.isBlank(keyword)) {
        //         return;
        //     }
        //     // 获取关键字回复配置
        //     WxmpReply condition = new WxmpReply();
        //     condition.setName(keyword);

        //     QueryWrapper<WxmpReply> queryWrapper = new QueryWrapper<>();
        //     queryWrapper.setEntity(condition);
        //     WxmpReply entity = condition.selectOne(queryWrapper);

        //     // 发送文本客服消息
        //     if (entity != null) {
        //         WxMpKefuMessage message = WxMpKefuMessage.TEXT()
        //                 .toUser(openid)
        //                 .content(entity.getReplyText())
        //                 .build();
        //         this.wxService.getKefuService().sendKefuMessage(message);
        //     }
        // } catch (WxErrorException e) {
        //     log.error("==========关键字回复异常==========");
        //     log.error("openid=" + openid);
        //     log.error("keyword=" + keyword);
        //     log.error(e.getMessage());
        // }
    }

    /**
     * 创建菜单
     *
     * @return String
     */
    public String menuCreate() {
        WxMenu wxMenu = null;//this.getMenu();
        try {
            this.wxService.getMenuService().menuCreate(wxMenu);
            return "Menu Create Succeed";
        } catch (WxErrorException e) {
            return e.getMessage();
        }
    }

    /**
     * 从DB取得默认菜单配置
     * 支持小程序跳转菜单
     *
     * @return WxMenu
     */
    // private WxMenu getMenu() {
    //     WxMenu wxMenu = new WxMenu();

    //     MenuDto condition = new MenuDto();
    //     QueryWrapper<MenuDto> queryWrapper = new QueryWrapper<>();
    //     queryWrapper.setEntity(condition);
    //     queryWrapper.orderByAsc("sort");

    //     List<MenuDto> menuList = condition.selectList(queryWrapper);

    //     for (MenuDto m : menuList) {
    //         if ("1".equals(m.getMenuLevel())) {
    //             WxMenuButton b1 = new WxMenuButton();
    //             b1.setType(m.getMenuType());
    //             b1.setName(m.getMenuName());
    //             b1.setKey(m.getMenuKey());
    //             b1.setUrl(m.getUrl());
    //             b1.setMediaId(m.getUrl());
    //             b1.setAppId(m.getAppid());
    //             b1.setPagePath(m.getPagepath());
    //             wxMenu.getButtons().add(b1);
    //         } else {
    //             WxMenuButton b2 = new WxMenuButton();
    //             b2.setType(m.getMenuType());
    //             b2.setName(m.getMenuName());
    //             b2.setKey(m.getMenuKey());
    //             b2.setUrl(m.getUrl());
    //             b2.setMediaId(m.getUrl());
    //             b2.setAppId(m.getAppid());
    //             b2.setPagePath(m.getPagepath());
    //             wxMenu.getButtons().get(wxMenu.getButtons().size() - 1).getSubButtons().add(b2);
    //         }

    //     }
    //     return wxMenu;
    // }
}
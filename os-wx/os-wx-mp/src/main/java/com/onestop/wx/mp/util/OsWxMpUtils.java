/*
 *
 *  * Copyright (c) 2021 os-parent Authors. All Rights Reserved.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *     http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package com.onestop.wx.mp.util;

import cn.hutool.core.util.StrUtil;
import com.onestop.common.core.exception.OsBizException;
import com.onestop.wx.mp.model.dto.MenuConfigs;
import com.onestop.wx.mp.model.dto.MenuDto;
import com.onestop.wx.mp.constant.WxMpConsts;
import com.onestop.wx.mp.model.dto.ReplyConfigs;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.bean.menu.WxMenu;
import me.chanjar.weixin.common.bean.menu.WxMenuButton;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.kefu.WxMpKefuMessage;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

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
    private WxMpService wxMpService;
    /**
     * 关键字回复配置类
     */
    @Autowired(required = false)
    private ReplyConfigs replyConfigs;

    /**
     * 获取openid
     *
     * @param code
     * @return Openid
     */
    public String getOpenid(String code) {
        try {
            String openid = this.wxMpService.getOAuth2Service().getAccessToken(code).getOpenId();
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

        String msgId = this.wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
    }

    /**
     * 关键字回复
     *
     * @param keyword
     */
    public void keywordReply(String openid, String keyword) {
        try {
            if (this.replyConfigs == null) {
                throw new OsBizException("配置项：os.wxmp.reply.enabled 未设置");
            }
            if (StrUtil.isBlank(openid) || StrUtil.isBlank(keyword)) {
                return;
            }

            String replyText = this.replyConfigs.getReplyText(keyword);
            if (StrUtil.isNotBlank(replyText)) {
                // 发送文本客服消息
                WxMpKefuMessage message = WxMpKefuMessage.TEXT()
                        .toUser(openid)
                        .content(replyText)
                        .build();
                this.wxMpService.getKefuService().sendKefuMessage(message);
            }
        } catch (WxErrorException e) {
            log.error("==========关键字回复异常==========");
            log.error("openid=" + openid);
            log.error("keyword=" + keyword);
            log.error(e.getMessage());
        }
    }

    /**
     * 创建菜单
     *
     * @param configs
     */
    public void menuCreate(MenuConfigs configs) {
        WxMenu wxMenu = this.getMenu(configs);
        try {
            this.wxMpService.getMenuService().menuCreate(wxMenu);
        } catch (WxErrorException e) {
            log.error("========menuCreate=======");
            log.error(e.getError().toString());
            throw new OsBizException(e.getError().getErrorCode(), e.getError().getErrorMsg());
        }
    }

    /**
     * 默认从配置文件中取得
     * 支持小程序跳转菜单
     *
     * @param configs
     * @return me.chanjar.weixin.common.bean.menu.WxMenu
     */
    private WxMenu getMenu(MenuConfigs configs) {
        WxMenu wxMenu = new WxMenu();

        List<MenuDto> menuList = configs.getConfigs();

        for (MenuDto m : menuList) {
            if (WxMpConsts.MenuLevel.Level1.equals(m.getMenuLevel())) {
                WxMenuButton b1 = new WxMenuButton();
                b1.setType(m.getMenuType());
                b1.setName(m.getMenuName());
                b1.setKey(m.getMenuKey());
                b1.setUrl(m.getUrl());
                b1.setMediaId(m.getUrl());
                b1.setAppId(m.getAppid());
                b1.setPagePath(m.getPagepath());
                wxMenu.getButtons().add(b1);
            } else {
                WxMenuButton b2 = new WxMenuButton();
                b2.setType(m.getMenuType());
                b2.setName(m.getMenuName());
                b2.setKey(m.getMenuKey());
                b2.setUrl(m.getUrl());
                b2.setMediaId(m.getUrl());
                b2.setAppId(m.getAppid());
                b2.setPagePath(m.getPagepath());
                wxMenu.getButtons().get(wxMenu.getButtons().size() - 1).getSubButtons().add(b2);
            }

        }
        return wxMenu;
    }
}
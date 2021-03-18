package com.onestop.wx.mp.config;

import com.onestop.wx.mp.handler.*;
import lombok.AllArgsConstructor;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static me.chanjar.weixin.common.api.WxConsts.*;

/**
 * 消息路由配置
 * @author Clark
 * @date 2020-08-25
 */
@AllArgsConstructor
@Configuration
public class WxMpMessageRouterConfig {
    private final MpMenuHandler menuHandler;
    private final MpMsgHandler msgHandler;
    private final MpScanHandler scanHandler;
    private final MpUnsubscribeHandler unsubscribeHandler;
    private final MpSubscribeHandler subscribeHandler;

    private WxMpService wxMpService;

    @Bean
    public WxMpMessageRouter router() {
        final WxMpMessageRouter newRouter = new WxMpMessageRouter(this.wxMpService);

        // 记录所有事件的日志 （异步执行）

        // 自定义菜单事件
        newRouter.rule().async(false).msgType(WxConsts.XmlMsgType.EVENT)
                .event(MenuButtonType.CLICK).handler(this.menuHandler).end();

        // 关注事件
        newRouter.rule().async(false).msgType(XmlMsgType.EVENT)
                .event(EventType.SUBSCRIBE).handler(this.subscribeHandler)
                .end();

        // 取消关注事件
        newRouter.rule().async(false).msgType(XmlMsgType.EVENT)
                .event(EventType.UNSUBSCRIBE)
                .handler(this.unsubscribeHandler).end();

        // 扫码事件
        newRouter.rule().async(false).msgType(XmlMsgType.EVENT)
                .event(EventType.SCAN).handler(scanHandler).end();
        newRouter.rule().async(false).msgType(XmlMsgType.EVENT)
                .event(EventType.SCANCODE_PUSH).handler(scanHandler).end();
        newRouter.rule().async(false).msgType(XmlMsgType.EVENT)
                .event(EventType.SCANCODE_WAITMSG).handler(scanHandler).end();

        // 默认
        newRouter.rule().async(false).handler(this.msgHandler).end();

        return newRouter;
    }
}

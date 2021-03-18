package com.onestop.wx.mp.handler;

import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 关注事件处理类
 *
 * @author Clark
 * @version 2020-08-08
 */
@Component
public class MpUnsubscribeHandler extends MpBaseHandler {

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
                                    Map<String, Object> context, WxMpService wxMpService,
                                    WxSessionManager sessionManager) {
        String openid = wxMessage.getFromUser();
        // 更新取消关注用户信息
        if (StringUtils.isNotBlank(openid)) {
            // TODO 取消关注逻辑
        }

        return null;
    }
}

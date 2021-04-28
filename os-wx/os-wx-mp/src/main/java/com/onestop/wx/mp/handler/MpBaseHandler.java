package com.onestop.wx.mp.handler;

import com.onestop.wx.mp.model.dto.WxmpReply;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * 消息处理基类
 *
 * @author Clark
 * @version 2018-05-08
 */
public abstract class MpBaseHandler implements WxMpMessageHandler {
    /**
     * 扫码关注EventKey前缀
     */
    public final static String QR_SCENE = "qrscene_";

    @Autowired
    public WxMpService wxMpService;

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService,
                                    WxSessionManager sessionManager) throws WxErrorException {
        return null;
    }

    /**
     * 构建关键字回复信息
     *
     * @param wxMessage
     * @return WxMpXmlOutMessage
     */
    public WxMpXmlOutMessage buildReply(WxMpXmlMessage wxMessage) {
        WxmpReply reply = this.getReply(wxMessage.getContent());

        if (reply != null) {
            switch (reply.getMsgType()) {
                case WxConsts.XmlMsgType.TEXT:
                    return WxMpXmlOutMessage.TEXT().content(reply.getReplyText())
                            .fromUser(wxMessage.getToUser()).toUser(wxMessage.getFromUser())
                            .build();
                default:
                    break;
            }
        }

        return null;
    }

    /**
     * 查询关键字回复
     *
     * @param keyword
     * @return 关键字回复
     */
    protected WxmpReply getReply(String keyword) {
//        // 获取关键字回复配置
//        WxmpReply condition = new WxmpReply();
//        condition.setKeyword(keyword);
//
//        QueryWrapper<WxmpReply> queryWrapper = new QueryWrapper<>();
//        queryWrapper.setEntity(condition);
//
//        return condition.selectOne(queryWrapper);
        return null;
    }

    /**
     * 根据参数创建永久二维码,返回二维码链接
     *
     * @param sceneStr
     * @return
     */
//    public String getQrcodeImgUrl(String sceneStr) {
//        try {
//            WxMpQrCodeTicket ticket = this.wxMpService.getQrcodeService().qrCodeCreateLastTicket(sceneStr);
//            String url = this.wxMpService.getQrcodeService().qrCodePictureUrl(ticket.getTicket(), Boolean.TRUE);
//            return url;
//        } catch (WxErrorException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
}

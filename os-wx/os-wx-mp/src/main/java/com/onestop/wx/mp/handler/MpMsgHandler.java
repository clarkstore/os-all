package com.onestop.wx.mp.handler;

import cn.hutool.core.util.StrUtil;
import com.onestop.wx.mp.constant.WxMpConsts;
import com.onestop.wx.mp.util.OsWxMpUtils;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 默认消息处理类
 *
 * @author Clark
 * @version 2020-08-11
 */
@Component
public class MpMsgHandler extends MpBaseHandler {

    @Autowired
    protected OsWxMpUtils wxMpCoreUtils;

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService,
                                    WxSessionManager sessionManager) {
//        if (wxMessage.getMsgType().equalsIgnoreCase(WxConsts.XmlMsgType.TEXT)) {
        // 接入多客服
        WxMpXmlOutMessage outMessage = this.transferKfService(wxMessage);
        if (outMessage != null) {
            return outMessage;
        }

        // 关键字回复
        outMessage = super.buildReply(wxMessage);
        if (outMessage != null) {
            return outMessage;
        }

        // 取得openid
        outMessage = this.getOpenid(wxMessage);
        if (outMessage != null) {
            return outMessage;
        }

//             创建菜单
        outMessage = this.createMenu(wxMessage);
        if (outMessage != null) {
            return outMessage;
        }

//            // 二维码
//            outMessage = this.getQrcode(wxMessage);
//            if (outMessage != null) {
//                return outMessage;
//            }
//        }
        return null;
    }

    /**
     * 转入多客服
     *
     * @param wxMessage
     * @return
     */
    protected WxMpXmlOutMessage transferKfService(WxMpXmlMessage wxMessage) {
        // 当用户输入关键词如“你好”，“客服”等，并且有客服在线时，把消息转发给在线客服
        if (StrUtil.startWithAny(wxMessage.getContent(), WxMpConsts.TransferKf.KEYWORDS)) {
            try {
                // 客服在线，接入多客服
                if (super.wxMpService.getKefuService().kfOnlineList()
                        .getKfOnlineList().size() > 0) {
                    // TODO WxMpXmlOutMessage.TRANSFER_CUSTOMER_SERVICE().kfAccount("").toUser("").build();
                    return WxMpXmlOutMessage.TRANSFER_CUSTOMER_SERVICE()
                            .fromUser(wxMessage.getToUser())
                            .toUser(wxMessage.getFromUser()).build();
                }
                // 返回非服务时间消息
                wxMessage.setContent(WxMpConsts.MsgReply.KEYWORD_KF_WORKINGTIME);
                return this.buildReply(wxMessage);
            } catch (WxErrorException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 取得openid
     *
     * @param wxMessage
     * @return
     */
    private WxMpXmlOutMessage getOpenid(WxMpXmlMessage wxMessage) {

        if (StrUtil.equalsIgnoreCase("openid", wxMessage.getContent())) {
            return WxMpXmlOutMessage.TEXT().content(wxMessage.getFromUser())
                    .fromUser(wxMessage.getToUser()).toUser(wxMessage.getFromUser())
                    .build();
        }

        return null;
    }

    /**
     * 创建菜单
     *
     * @param wxMessage
     * @return
     */
    private WxMpXmlOutMessage createMenu(WxMpXmlMessage wxMessage) {

        if (StrUtil.equalsIgnoreCase("51CM", wxMessage.getContent())) {
            String result = this.wxMpCoreUtils.menuCreate();
            return WxMpXmlOutMessage.TEXT().content(result)
                    .fromUser(wxMessage.getToUser()).toUser(wxMessage.getFromUser())
                    .build();
        }

        return null;
    }

    /**
     * 创建二维码
     *
     * @param wxMessage
     * @return
     */
    private WxMpXmlOutMessage getQrcode(WxMpXmlMessage wxMessage) {
//        // 当用户输入关键词如“qrcode”，“二维码”等，创建二维码
//        Config config = super.wechatUtils.getConfig(WechatConsts.WxConfig.Qrcode.Group,
//                WechatConsts.WxConfig.Qrcode.Key);
//        if (config != null) {
//            String[] keyword = StrUtil.splitToArray(config.getValue(), StrUtil.C_COMMA);
//
//            if (StrUtil.startWithAny(wxMessage.getContent(), keyword)) {
//                try {
//                    // 业务代码-openid
//                    String sceneStr = "share" + StrUtil.DASHED + wxMessage.getFromUser();
//                    // 取得二维码地址
//                    URL urlObj = new URL(super.getQrcodeImgUrl(sceneStr));
//                    BufferedImage bi = ImageUtil.read(urlObj);
//
//                    // 生成分享图片、附加个人二维码水印
//                    File srcImageFile = new File(super.getMpBizConfig().getUploadPath() + "/share.jpeg");
//                    Image newBi = ImgUtil.pressImage(ImageIO.read(srcImageFile), ImgUtil.scale(bi, 0.4f),
//                            -45, 150, 1);
//                    ImgUtil.write(newBi, new File(super.getMpBizConfig().getUploadPath() + sceneStr + ".jpeg"));
//                    // 分享页面地址
//                    String url = super.getMpBizConfig().getServerFullUrl() + "/qrcode/show?name=" + sceneStr + ".jpeg";
//                    // 转成短链接
//                    String shortUrl = super.getService().shortUrl(url);
//                    return new TextBuilder().build(shortUrl, wxMessage, this.getService());
//                } catch (WxErrorException e) {
//                    e.printStackTrace();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }

        return null;
    }
}

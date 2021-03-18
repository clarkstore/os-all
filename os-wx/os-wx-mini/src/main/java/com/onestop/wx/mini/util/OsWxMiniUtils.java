package com.onestop.wx.mini.util;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaPhoneNumberInfo;
import cn.binarywang.wx.miniapp.bean.WxMaRunStepInfo;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import cn.binarywang.wx.miniapp.constant.WxMaConstants;
import cn.hutool.core.io.FileUtil;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.bean.result.WxMediaUploadResult;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;

/**
 * 微信小程序API工具类
 *
 * @author Clark
 * @date 2020-08-31
 */
@Slf4j
@Component
public class OsWxMiniUtils {
//    @Value("${wx.mediaPath}")
    private String mediaPath;
    @Autowired
    private WxMaService wxService;

    /**
     * 登录（code2Session）
     *
     * @param code
     * @return
     */
    public WxMaJscode2SessionResult code2Session(String code) {
        try {
            WxMaJscode2SessionResult session = this.wxService.getUserService().getSessionInfo(code);
            return session;
        } catch (WxErrorException e) {
            log.error("==========code2Session异常==========");
            log.error(e.getMessage());
        }
        return null;
    }

    /**
     * 用户信息校验
     *
     * @param sessionKey
     * @param signature
     * @param rawData
     * @return
     */
    public boolean checkUserInfo(String sessionKey,
                                 String signature, String rawData) {
        return this.wxService.getUserService().checkUserInfo(sessionKey, rawData, signature);
    }

    /**
     * 解密用户信息
     *
     * @param sessionKey
     * @param encryptedData
     * @param iv
     * @return
     */
    public WxMaUserInfo getUserInfo(String sessionKey, String encryptedData, String iv) {
        WxMaUserInfo userInfo = this.wxService.getUserService().getUserInfo(sessionKey, encryptedData, iv);
        return userInfo;
    }

    /**
     * 获取用户绑定手机号信息
     *
     * @param sessionKey
     * @param encryptedData
     * @param iv
     * @return
     */
    public WxMaPhoneNumberInfo getPhoneNoInfo(String sessionKey, String encryptedData, String iv) {
        WxMaPhoneNumberInfo phoneNoInfo = this.wxService.getUserService().getPhoneNoInfo(sessionKey, encryptedData, iv);
        return phoneNoInfo;
    }

    /**
     * 获取用户运动信息
     *
     * @param sessionKey
     * @param encryptedData
     * @param iv
     * @return
     */
    public List<WxMaRunStepInfo> getRunStepInfo(String sessionKey, String encryptedData, String iv) {
        List<WxMaRunStepInfo> list = this.wxService.getRunService().getRunStepInfo(sessionKey, encryptedData, iv);
        return list;
    }

    /**
     * 发送订阅消息
     *
     * @param subscribeCode
     * @param openid
     * @param valueList
     * @throws WxErrorException
     */
    public void sendSubscribeMsg(String subscribeCode, String openid, List<String> valueList) throws WxErrorException {
//        //获取订阅消息配置
//        SubscribeDto subscribe = this.subscribeProperties.getConfig(subscribeCode);
//        WxMaSubscribeMessage subscribeMessage = WxMaSubscribeMessage.builder()
//                .toUser(openid)
//                .templateId(subscribe.getTplId())
//                //TODO 生产环境修改为：WxMaConstants.MiniprogramState.FORMAL
//                .miniprogramState(WxMaConstants.MiniProgramState.DEVELOPER)
//                .page(subscribe.getPage())
//                .build();
//
//        //封装参数
//        for (int i = 0; i < subscribe.getNameList().size(); i++) {
//            WxMaSubscribeMessage.Data data = new WxMaSubscribeMessage.Data();
//            data.setName(subscribe.getNameList().get(i));
//            data.setValue(valueList.get(i));
//            subscribeMessage.addData(data);
//        }
//
//        this.wxService.getMsgService().sendSubscribeMsg(subscribeMessage);
    }

    /**
     * 上传临时素材
     *
     * @param fileName
     * @return
     * @throws WxErrorException
     */
    public String uploadMedia(String fileName) throws WxErrorException {
        String fillFileName = this.mediaPath + fileName + ".png";
        File file = FileUtil.file(fillFileName);
        WxMediaUploadResult uploadResult = this.wxService.getMediaService().uploadMedia(WxMaConstants.KefuMsgType.IMAGE, file);
        return uploadResult.getMediaId();
    }

    /**
     * 客户消息-小程序卡片
     *
     * @param tplCode
     * @param openid
     * @throws WxErrorException
     */
//    public void sendKefuMsg4MaPage(String tplCode, String openid) throws WxErrorException {
//        String mediaId = this.uploadMedia(tplCode);
//
//        WxMaKefuMessage kefuMessage = new WxMaKefuMessage();
//        kefuMessage.setToUser(openid);
//        kefuMessage.setMsgType(WxMaConstants.KefuMsgType.MA_PAGE);
//        WxMaKefuMessage.newMaPageBuilder().title(title).pagePath("pages/user/index/index").thumbMediaId(mediaId).build();
//        this.wxService.getMsgService().sendKefuMsg(kefuMessage);
//    }
}

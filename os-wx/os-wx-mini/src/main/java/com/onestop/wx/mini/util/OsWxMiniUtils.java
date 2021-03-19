package com.onestop.wx.mini.util;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.*;
import com.onestop.common.core.util.Res;
import com.onestop.wx.mini.util.dto.SubscribeConfigs;
import com.onestop.wx.mini.util.dto.SubscribeDto;
import com.onestop.wx.mini.util.dto.SubscribeReqDto;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 微信小程序API工具类
 *
 * @author Clark
 * @version 2021-03-18
 */
@Slf4j
@Component
public class OsWxMiniUtils {
    //    @Value("${wx.mediaPath}")
    private String mediaPath;
    @Autowired
    private WxMaService wxService;
    @Autowired
    private SubscribeConfigs subscribeConfigs;

    /**
     * 发送订阅消息
     *
     * @param dto     订阅消息请求类
     * @throws WxErrorException WxErrorException
     */
    public void sendSubscribeMsg(SubscribeReqDto dto) throws WxErrorException {
        log.debug("---------------sendSubscribeMsg----------------");
        log.debug("dto : " + dto.toString());
        //获取订阅消息配置
        SubscribeDto subscribe = this.subscribeConfigs.getConfig(dto.getMsgId());
        WxMaSubscribeMessage subscribeMessage = WxMaSubscribeMessage.builder()
                .toUser(dto.getOpenid())
                .templateId(subscribe.getTplId())
                .miniprogramState(this.subscribeConfigs.getMiniprogramState())
                .page(subscribe.getPage())
                .build();

        //封装参数
        for (int i = 0; i < subscribe.getNameList().size(); i++) {
            WxMaSubscribeMessage.Data data = new WxMaSubscribeMessage.Data();
            data.setName(subscribe.getNameList().get(i));
            data.setValue(dto.getValueList().get(i));
            subscribeMessage.addData(data);
        }

        try {
            this.wxService.getMsgService().sendSubscribeMsg(subscribeMessage);
        } catch (WxErrorException e) {
            log.error("---------------sendSubscribeMsg----------------");
            log.error("dto : " + dto.toString());
            log.error(e.getError().toString());
            throw e;
        }

    }

//    /**
//     * 上传临时素材
//     *
//     * @param fileName
//     * @return
//     * @throws WxErrorException
//     */
//    public String uploadMedia(String fileName) throws WxErrorException {
//        String fillFileName = this.mediaPath + fileName + ".png";
//        File file = FileUtil.file(fillFileName);
//        WxMediaUploadResult uploadResult = this.wxService.getMediaService().uploadMedia(WxMaConstants.KefuMsgType.IMAGE, file);
//        return uploadResult.getMediaId();
//    }

//    /**
//     * 客户消息-小程序卡片
//     *
//     * @param tplCode
//     * @param openid
//     * @throws WxErrorException
//     */
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

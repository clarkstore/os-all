package com.onestop.wx.mini.util;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaSubscribeMessage;
import com.onestop.wx.mini.util.dto.SubscribeConfigs;
import com.onestop.wx.mini.util.dto.SubscribeDto;
import com.onestop.wx.mini.util.dto.SubscribeReqDto;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;

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
     * @param dto 订阅消息请求类
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

    /**
     * 获取小程序码（永久有效、数量暂无限制）
     * 供自定义接口使用，必传filepath
     * @param scene 最大32个可见字符，只支持数字，大小写英文以及部分特殊字符：!#$&'()*+,/:;=?@-._~，其它字符请自行编码为合法字符（因不支持%，中文无法使用 urlencode 处理，请使用其他编码方式）
     * @param page 必须是已经发布的小程序存在的页面（否则报错），例如 pages/index/index, 根路径前不要填加 /,不能携带参数（参数请放在scene字段里），如果不填写这个字段，默认跳主页面
     * @param filePath 二维码存放路径
     * @return File
     * @throws WxErrorException WxErrorException
     */
    public File createWxaCodeUnlimit(String scene, String page, String filePath) throws WxErrorException {
        try {
            return this.wxService.getQrcodeService().createWxaCodeUnlimit(scene, page, filePath);
        } catch (WxErrorException e) {
            log.error("---------------createWxaCodeUnlimit----------------");
            log.error("scene : " + scene);
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

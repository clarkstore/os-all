package com.onestop.wx.mini.api;

import cn.binarywang.wx.miniapp.api.WxMaMsgService;
import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaKefuMessage;
import cn.binarywang.wx.miniapp.constant.WxMaConstants;
import cn.hutool.core.collection.CollUtil;
import com.onestop.common.core.util.Res;
import com.onestop.wx.mini.util.OsWxMiniUtils;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 微信小程序用户接口
 *
 * @author Clark
 * @version 2020-08-23
 */
@Slf4j
@RestController
@RequestMapping("wxmini/api/msg")
public class WxMiniMsgApi {
    @Autowired
    private WxMaService wxService;
    @Autowired
    private OsWxMiniUtils coreUtils;
//    @Autowired
//    private WxMaSubscribeProperties subscribeproperties;

    /**
     * 登陆接口
     *
     * @return Res
     */
    @GetMapping("/signIn")
    public Res signIn() {
        try {
            List<String> valueList = CollUtil.newArrayList();
            valueList.add("常洪源");
            valueList.add("2020-02-09 16:51");
            this.coreUtils.sendSubscribeMsg("signIn","ohsEJ0f4BYyFXdK9sXUr28zXNr08", valueList);
            valueList = CollUtil.newArrayList();
            valueList.add("-500");
            valueList.add("2020-02-09 16:51");
            valueList.add("兑换礼品");
            this.coreUtils.sendSubscribeMsg("point","ohsEJ0f4BYyFXdK9sXUr28zXNr08", valueList);
            return Res.ok();
        } catch (WxErrorException e) {
            log.error(e.getMessage(), e);
            return Res.failed(e.getError().getErrorMsg());
        }
    }

//    /**
//     * 客服消息
//     */
//    @GetMapping("/kefu")
//    public Res kefu() {
//        final WxMaMsgService service = this.wxService.getMsgService();
//
//        WxMaKefuMessage kefuMessage = new WxMaKefuMessage();
//        kefuMessage.setToUser("ohsEJ0f4BYyFXdK9sXUr28zXNr08");
//        kefuMessage.setMsgType(WxMaConstants.KefuMsgType.TEXT);
//        kefuMessage.setText(new WxMaKefuMessage.KfText("test"));
//
//        try {
//            service.sendKefuMsg(kefuMessage);
//            //TODO 可以增加自己的逻辑，关联业务相关数据
//            return Res.ok();
//        } catch (WxErrorException e) {
//            log.error(e.getMessage(), e);
//            return Res.failed(e.getError().getErrorMsg());
//        }
//    }
}

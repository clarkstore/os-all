package com.onestop.wx.mini.api;

import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaPhoneNumberInfo;
import cn.binarywang.wx.miniapp.bean.WxMaRunStepInfo;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import com.onestop.common.core.util.Res;
import com.onestop.wx.mini.util.JsonUtils;
import com.onestop.wx.mini.util.OsWxMiniUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 微信小程序用户接口-demo
 * 如需扩展：业务模块中自行实现
 *
 * @author Clark
 * @version 2021-03-18
 */
@Slf4j
@RestController
@RequestMapping("wxmini/api/user")
public class WxMiniUserApi {
    @Autowired
    private OsWxMiniUtils wxMiniUtils;

    /**
     * 登陆接口
     *
     * @param code code
     * @return Res
     */
    @GetMapping("/login")
    public Res login(String code) {
        if (StringUtils.isBlank(code)) {
            return Res.failed("empty jscode");
        }

        WxMaJscode2SessionResult session = this.wxMiniUtils.code2Session(code);
        //TODO 可以增加自己的逻辑，关联业务相关数据
        return Res.ok(session);
    }

    /**
     * 获取用户信息接口
     *
     * @param sessionKey sessionKey
     * @param signature signature
     * @param rawData rawData
     * @param encryptedData 加密数据
     * @param iv iv
     * @return Res
     */
    @GetMapping("/info")
    public Res info(String sessionKey,
                    String signature, String rawData, String encryptedData, String iv) {
        // 用户信息校验
        if (!this.wxMiniUtils.checkUserInfo(sessionKey, rawData, signature)) {
            return Res.failed("user check failed");
        }

        // 解密用户信息
        WxMaUserInfo userInfo = this.wxMiniUtils.getUserInfo(sessionKey, encryptedData, iv);

        return Res.ok(userInfo);
    }

    /**
     * 获取用户绑定手机号信息
     *
     * @param sessionKey sessionKey
     * @param signature signature
     * @param rawData rawData
     * @param encryptedData 加密数据
     * @param iv iv
     * @return Res
     */
    @GetMapping("/phone")
    public String phone(String sessionKey, String signature,
                        String rawData, String encryptedData, String iv) {
        // 用户信息校验
        if (!this.wxMiniUtils.checkUserInfo(sessionKey, rawData, signature)) {
            return "user check failed";
        }

        // 获取用户绑定手机号信息
        WxMaPhoneNumberInfo phoneNoInfo = this.wxMiniUtils.getPhoneNoInfo(sessionKey, encryptedData, iv);

        return JsonUtils.toJson(phoneNoInfo);
    }

    /**
     * 获取用户运动信息
     * @param sessionKey sessionKey
     * @param encryptedData 加密数据
     * @param iv iv
     * @return Res
     */
    @GetMapping("/runData")
    public Res runData(String sessionKey, String encryptedData, String iv) {
        List<WxMaRunStepInfo> list = this.wxMiniUtils.getRunStepInfo(sessionKey, encryptedData, iv);

        return Res.ok(list);
    }
}

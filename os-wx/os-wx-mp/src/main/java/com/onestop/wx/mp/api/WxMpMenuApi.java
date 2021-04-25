package com.onestop.wx.mp.api;

import com.onestop.common.core.util.Res;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 微信小程序用户接口
 *
 * @author Clark
 * @version 2020-08-23
 */
@Slf4j
@RestController
@RequestMapping("${os.wxmp.apiPath}/menu")
public class WxMpMenuApi {
    @GetMapping("/create")
    public Res send() {
        // try {
            return Res.ok();
        // } catch (WxErrorException e) {
        //     log.error(e.getMessage(), e);
        //     return Res.failed(e.getError().getErrorMsg());
        // }
    }
}

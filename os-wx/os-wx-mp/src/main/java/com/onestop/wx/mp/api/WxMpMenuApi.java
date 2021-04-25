package com.onestop.wx.mp.api;

import com.onestop.common.core.exception.BizException;
import com.onestop.common.core.util.Res;
import com.onestop.wx.mp.extra.dto.MenuConfigs;
import com.onestop.wx.mp.util.OsWxMpUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 微信服务号菜单接口
 *
 * @author Clark
 * @version 2020-08-23
 */
@Slf4j
@RestController
@RequestMapping("${os.wxmp.apiPath}/menu")
public class WxMpMenuApi {
    @Autowired(required = false)
    private MenuConfigs menuConfigs;
    @Autowired
    private OsWxMpUtils osWxMpUtils;

    @GetMapping("/create")
    public Res create() {
        try {
            this.osWxMpUtils.menuCreate(this.menuConfigs);
            return Res.ok();
        } catch (BizException e) {
            return Res.failed(e.getMsg());
        } catch (NullPointerException e) {
            return Res.failed("配置menu.isopen未启用");
        }
    }

    @PostMapping("/create")
    public Res create(MenuConfigs menu) {
        try {
            this.osWxMpUtils.menuCreate(menu);
            return Res.ok();
        } catch (BizException e) {
            return Res.failed(e.getMsg());
        }
    }
}

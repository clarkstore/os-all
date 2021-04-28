package com.onestop.wx.mp.api;

import cn.hutool.json.JSONUtil;
import com.onestop.common.core.exception.BizException;
import com.onestop.common.core.util.Res;
import com.onestop.wx.mp.model.dto.MenuConfigs;
import com.onestop.wx.mp.util.OsWxMpUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
            log.error("================");
            log.error("menuConfigs=" + JSONUtil.toJsonStr(this.menuConfigs));
            this.osWxMpUtils.menuCreate(this.menuConfigs);
            return Res.ok("菜单构建成功");
        } catch (BizException e) {
            return Res.failed(e.getMsg());
        } catch (NullPointerException e) {
            return Res.failed("配置项：menu.isopen未设置");
        }
    }

    @PostMapping("/create")
    public Res create(@RequestBody MenuConfigs menu) {
        try {
            this.osWxMpUtils.menuCreate(menu);
            return Res.ok("菜单构建成功");
        } catch (BizException e) {
            return Res.failed(e.getMsg());
        }
    }
}
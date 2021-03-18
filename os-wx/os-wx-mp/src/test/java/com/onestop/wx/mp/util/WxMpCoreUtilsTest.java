package com.onestop.wx.mp.util;

import com.onestop.wx.mp.MpApplication;
import com.onestop.wx.mp.extra.entity.WxmpUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = MpApplication.class)
public class WxMpCoreUtilsTest {
    @Autowired
    private WxMpCoreUtils wxMpCoreUtils;

    @Test
    public void getWxUser() {
        WxmpUser user = this.wxMpCoreUtils.getWxUser("123");
        System.out.println("user : " + user.toString());
    }

    @Test
    public void menuCreate() {
        this.wxMpCoreUtils.menuCreate();
    }
}
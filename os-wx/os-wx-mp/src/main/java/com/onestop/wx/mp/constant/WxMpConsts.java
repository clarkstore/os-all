package com.onestop.wx.mp.constant;

import me.chanjar.weixin.common.api.WxConsts;

/**
 * 微信服务号常量类
 * @author Clark
 * @date 2020/08/06
 */
public class WxMpConsts extends WxConsts {
    /**
     * 菜单Key
     */
    public static class MenuKey {
        /**
         * 身份验证关键字
         */
        public static final String Auth = "auth";
        /**
         * 多客服关键字
         */
        public static final String KfService = "kf";
        /**
         * 回复关键字
         */
        public static final String Reply = "reply";
    }

    /**
     * 消息回复规则
     */
    public static class MsgReply {
        /**
         * 关注回复
         */
        public static final String REPLY_TYPE_SUBSCRIBE = "01";
        /**
         * 关键字回复
         */
        public static final String REPLY_TYPE_KEYWORD = "02";
        /**
         * 客服消息回复
         */
        public static final String REPLY_TYPE_KF = "03";
        /**
         * 关注欢迎语
         */
        public static final String KEYWORD_SUBSCRIBE = "subscribe";
        /**
         * 默认回复关键字
         */
        public static final String KEYWORD_DEFAULT = "default";
        /**
         * 多客服欢迎语
         */
        public static final String KEYWORD_KF_GREETING = "kfGreeting";
        /**
         * 多客服会话结束
         */
        public static final String KEYWORD_KF_CLOSE = "kfClose";
        /**
         * 多客服工作时间
         */
        public static final String KEYWORD_KF_WORKINGTIME = "kfWorkingTime";
    }

    /**
     * 接入多客服关键字列表
     */
    public static class TransferKf {
        public static final String[] KEYWORDS = {"你好", "客服"};
    }
}

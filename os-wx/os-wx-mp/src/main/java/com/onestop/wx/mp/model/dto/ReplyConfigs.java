package com.onestop.wx.mp.model.dto;

import cn.hutool.core.collection.CollUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Map;

/**
 * 关键字回复
 *
 * @author Clark
 * @version 2021/04/23
 */
@Getter
@Setter
@ToString
public class ReplyConfigs {
    /**
     * 关键字回复配置
     */
    private List<ReplyDto> configs;
    /**
     * 文本关键字回复
     */
    private Map<String, String> replyTextMap;

    /**
     * 取得文本关键字回复Map
     * @return Map<String, String>
     */
    public Map<String, String> getReplyTextMap() {
        if (this.replyTextMap == null) {
            this.replyTextMap = CollUtil.newHashMap();

            if (this.configs != null) {
                this.configs.forEach(item -> {
                    this.replyTextMap.put(item.getKeyword(), item.getReplyText());
                });
            }
        }

        return this.replyTextMap;
    }

    /**
     * 取得关键字回复文本
     * @param keyword
     * @return 关键字回复文本
     */
    public String getReplyText(String keyword) {
        return this.getReplyTextMap().get(keyword);
    }
}

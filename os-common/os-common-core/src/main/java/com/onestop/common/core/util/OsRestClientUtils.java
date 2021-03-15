package com.onestop.common.core.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * RestTemplate客户端工具类
 * @author Clark
 * @version 2020-12-24
 */
//@Component
public class OsRestClientUtils {
    @Autowired
    private RestTemplate restTemplate;

    public String doGet(String url) {
        return restTemplate.getForObject(url, String.class);
    }

    public <T> T doGet(String url, Class<T> clasz) {
        return restTemplate.getForObject(url, clasz);
    }

    public <T> T doPost(String url, Object body, Class<T> clasz) {
        return restTemplate.postForObject(url, body, clasz);
    }
}

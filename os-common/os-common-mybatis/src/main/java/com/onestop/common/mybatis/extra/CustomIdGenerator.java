package com.onestop.common.mybatis.extra;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 自定义ID生成器
 *
 * @author Clark
 * @version 2021-05-26
 */
@Slf4j
@Component
public class CustomIdGenerator implements IdentifierGenerator {

    @Override
    public Long nextId(Object entity) {
        return IdUtil.getSnowflake(1, 1).nextId();
    }
}
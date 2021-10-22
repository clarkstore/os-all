/*
 *
 *  * Copyright (c) 2021 os-parent Authors. All Rights Reserved.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *     http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package com.onestop.starter.ali.rocketmq.autoconfigure;

import com.aliyun.openservices.ons.api.bean.OrderProducerBean;
import com.aliyun.openservices.ons.api.bean.ProducerBean;
import com.aliyun.openservices.ons.api.bean.TransactionProducerBean;
import com.aliyun.openservices.ons.api.transaction.LocalTransactionChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * os-ali-rocketmq配置
 *
 * @author Clark
 * @version 2021-10-20
 */
@Configuration
@EnableConfigurationProperties(OsRocketMqProperties.class)
@ConditionalOnProperty(prefix = "os.rocketmq", name = "enabled", havingValue = "true")
public class OsAliRocketmqAutoConfiguration {
    @Autowired
    private OsRocketMqProperties properties;

    @Autowired
    private LocalTransactionChecker localTransactionChecker;

    /**
     * 普通消息生产者
     *
     * @return
     */
    @Bean(initMethod = "start", destroyMethod = "shutdown")
    public ProducerBean buildProducer() {
        ProducerBean producer = new ProducerBean();
        producer.setProperties(properties.getMqPropertie());
        return producer;
    }

    /**
     * 顺序消息生产者
     *
     * @return
     */
    @Bean(initMethod = "start", destroyMethod = "shutdown")
    public OrderProducerBean buildOrderProducer() {
        OrderProducerBean orderProducerBean = new OrderProducerBean();
        orderProducerBean.setProperties(properties.getMqPropertie());
        return orderProducerBean;
    }

    /**
     * 事务消息生产者
     *
     * @return
     */
    @Bean(initMethod = "start", destroyMethod = "shutdown")
    public TransactionProducerBean buildTransactionProducer() {
        TransactionProducerBean producer = new TransactionProducerBean();
        producer.setProperties(properties.getMqPropertie());
        producer.setLocalTransactionChecker(localTransactionChecker);
        return producer;
    }
}

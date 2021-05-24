package com.onestop.starter.common.redis.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

/**
 * Token配置
 *
 * @author Clark
 * @version 2021-05-10
 */
@Configuration
@EnableConfigurationProperties(OsRedisProperties.class)
@ConditionalOnProperty(prefix = "os.redis", name = "enabled", havingValue = "true")
public class OsRedisAutoConfiguration {
    @Autowired
    private OsRedisProperties properties;

    @Bean
    public LettuceConnectionFactory lettuceConnectionFactory() {
        GenericObjectPoolConfig genericObjectPoolConfig = new GenericObjectPoolConfig();
        if (this.properties != null && this.properties.getLettuce() != null && this.properties.getLettuce().getPool() != null) {
            genericObjectPoolConfig.setMaxIdle(this.properties.getLettuce().getPool().getMaxIdle());
            genericObjectPoolConfig.setMinIdle(this.properties.getLettuce().getPool().getMinIdle());
            genericObjectPoolConfig.setMaxTotal(this.properties.getLettuce().getPool().getMaxActive());
            genericObjectPoolConfig.setMaxWaitMillis(this.properties.getLettuce().getPool().getMaxWait());
        }

        genericObjectPoolConfig.setTimeBetweenEvictionRunsMillis(100);
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName(this.properties.getHost());
        redisStandaloneConfiguration.setPort(this.properties.getPort());
        redisStandaloneConfiguration.setPassword(RedisPassword.of(this.properties.getPassword()));
        redisStandaloneConfiguration.setDatabase(this.properties.getDatabase());

        LettuceClientConfiguration clientConfig = LettucePoolingClientConfiguration.builder()
                .commandTimeout(Duration.ofMillis(this.properties.getTimeout()))
                .shutdownTimeout(Duration.ofMillis(this.properties.getLettuce().getShutdownTimeout()))
                .poolConfig(genericObjectPoolConfig)
                .build();

        LettuceConnectionFactory factory = new LettuceConnectionFactory(redisStandaloneConfiguration, clientConfig);
//        factory.setShareNativeConnection(true);
//        factory.setValidateConnection(false);
        return factory;
    }

    @Bean
    @ConditionalOnMissingBean
    public RedisTemplate<String, Object> redisTemplate(LettuceConnectionFactory factory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);

        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);

        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        // key采用String的序列化方式
        template.setKeySerializer(stringRedisSerializer);
        // hash的key也采用String的序列化方式
        template.setHashKeySerializer(stringRedisSerializer);
        // value序列化方式采用jackson
        template.setValueSerializer(jackson2JsonRedisSerializer);
        // hash的value序列化方式采用jackson
        template.setHashValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();

        return template;
    }

//    @Bean
//    @DependsOn("redisTemplate")
//    @ConditionalOnMissingBean
//    public OsRedisUtils osRedisUtils() {
//        OsRedisUtils utils = new OsRedisUtils();
//        return utils;
//    }
}

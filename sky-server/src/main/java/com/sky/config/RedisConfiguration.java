package com.sky.config;

import com.fasterxml.jackson.databind.ser.std.StringSerializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: cyr
 * @Date: 2024/01/22/20:35
 * @Description:
 */
@Configuration
@Slf4j
public class RedisConfiguration {
    @Bean//第三方bean对象
    public RedisTemplate redisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        //redis默认是jdk key序列化导致数据与原始数据不一致
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        return redisTemplate;

    }
}

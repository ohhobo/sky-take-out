package com.sky.config;

import com.fasterxml.jackson.databind.ser.std.StringSerializer;
import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnel;
import com.google.common.hash.Funnels;
import com.sky.bloom.BloomFilterHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
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
@EnableCaching
public class RedisConfiguration {
    @Bean//第三方bean对象
    public RedisTemplate redisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        //redis默认是jdk key序列化导致数据与原始数据不一致
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        return redisTemplate;

    }

    @Bean
    public BloomFilterHelper<Integer> initBloomFilterHelper(){
//        return new BloomFilterHelper<>(
//                (Funnel<String>) (from, into) -> into.putString(from,Charsets.UTF_8)
//                        .putString(from, Charsets.UTF_8), 1000000, 0.01);
        return new BloomFilterHelper<>(Funnels.integerFunnel(),100000,0.01);
    }

}

package com.tomatoman.redis.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
@EnableAutoConfiguration
public class RedisConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.redis.pool")
    public JedisPoolConfig getRedisConfig(){
        JedisPoolConfig config = new JedisPoolConfig();
        return config;
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.redis")
    public JedisConnectionFactory getConnectionFactory() {
        JedisConnectionFactory factory = new JedisConnectionFactory();
        factory.setUsePool(true);
        JedisPoolConfig config = getRedisConfig();
        factory.setPoolConfig(config);
        return factory;
    }

    @Bean(name = "redisTemplate")
    public RedisTemplate getRedisTemplate() {
        JedisConnectionFactory factory = getConnectionFactory();

        RedisTemplate template = new RedisTemplate();
        template.setConnectionFactory(factory);

        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        JdkSerializationRedisSerializer jdkSerializationRedisSerializer = new JdkSerializationRedisSerializer();

        template.setDefaultSerializer(stringRedisSerializer);
        template.setKeySerializer(stringRedisSerializer);
        template.setValueSerializer(stringRedisSerializer);
        template.setHashKeySerializer(stringRedisSerializer);
        template.setHashValueSerializer(stringRedisSerializer);

        //template.afterPropertiesSet();
        return template;


//        RedisTemplate<String, String> redis = new RedisTemplate<>();
//        redis.setConnectionFactory(getConnectionFactory());
//
//        // 设置redis的String/Value的默认序列化方式
//        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
//        redis.setKeySerializer(stringRedisSerializer);
//        redis.setValueSerializer(stringRedisSerializer);
//        redis.setHashKeySerializer(stringRedisSerializer);
//        redis.setHashValueSerializer(stringRedisSerializer);
//
//        redis.afterPropertiesSet();
//        return redis;
    }

}

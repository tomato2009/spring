package com.tomatoman.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisZSetCommands;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisHyperLogLogTest {
    @Resource
    private RedisTemplate redisTemplate;

    String key1 = "hyperloglog1";
    String key2 = "hyperloglog2";
    String key3 = "hyperloglog3";

    /**
     * ／／相当于lpush 把多个价值从左插入链表
     */
    @Test
    public void add() {
        redisTemplate.opsForHyperLogLog().add(key1, "a", "b","c","d","e","a","b");
        redisTemplate.opsForHyperLogLog().add(key2, "a");
        redisTemplate.opsForHyperLogLog().add(key2, "z");
    }

    /**
     * ／／统计基数
     */
    @Test
    public void size() {
        System.out.println(redisTemplate.opsForHyperLogLog().size(key1));
        System.out.println(redisTemplate.opsForHyperLogLog().size(key2));
    }

    @Test
    public void union() {
        redisTemplate.opsForHyperLogLog().union(key3, key1, key2);
        System.out.println(redisTemplate.opsForHyperLogLog().size(key3));
    }
}
package com.tomatoman.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisListCommands;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisSetTest {
    @Resource
    private RedisTemplate<String, String> redisTemplate;

    String key1 = "set1";
    String key2 = "set2";

    /**
     * ／／相当于lpush 把多个价值从左插入链表
     */
    @Test
    public void addSet() {
        redisTemplate.boundSetOps(key1).add("v1","v2","v3","v4","v5","v6");
        redisTemplate.opsForSet().add(key2, "v0","v2","v4","v6","v8");
    }

    /**
     * ／／获取集合所有元素
     */
    @Test
    public void members() {
        System.out.println(redisTemplate.opsForSet().members(key1));
    }

    @Test
    public void size() {
        System.out.println(redisTemplate.opsForSet().size(key1));
    }

    /**
     * ／／求差集
     */
    @Test
    public void diff() {
        System.out.println(redisTemplate.opsForSet().difference(key2, key1));
    }

    /**
     * ／／求交集
     */
    @Test
    public void inter() {
        System.out.println(redisTemplate.opsForSet().intersect(key2, key1));
    }

    /**
     *
     */
    @Test
    public void isMember() {
        System.out.println(redisTemplate.opsForSet().isMember(key1, "v1"));
        System.out.println(redisTemplate.opsForSet().isMember(key1, "v11"));
    }

    /**
     * 随机pop一个
     */
    @Test
    public void pop() {
        System.out.println(redisTemplate.opsForSet().pop(key1));
    }

    /**
     * 随机获取一个
     */
    @Test
    public void randomMember() {
        System.out.println(redisTemplate.opsForSet().randomMember(key1));
    }
}

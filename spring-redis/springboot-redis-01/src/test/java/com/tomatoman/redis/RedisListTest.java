package com.tomatoman.redis;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisListCommands;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisListTest {
    @Resource
    private RedisTemplate<String, String> redisTemplate;

    String key = "list";

    /**
     * ／／相当于lpush 把多个价值从左插入链表
     */
    @Test
    public void leftPushList() {
        List<String> list = new ArrayList<String>(3);
        list.add("1");
        list.add("2");
        list.add("3");

        redisTemplate.opsForList().leftPushAll(key, list);
    }

    @Test
    public void leftPush() {
        redisTemplate.opsForList().leftPush(key, "4");
        redisTemplate.opsForList().rightPush(key, "5");
    }

    @Test
    public void rightPushList() {
        List<String> list = new ArrayList<String>(3);
        list.add("6");
        list.add("7");
        list.add("8");

        redisTemplate.opsForList().rightPushAll(key, list);
    }

    @Test
    public void index() {
        System.out.println(redisTemplate.opsForList().index(key, 1));
    }

    @Test
    public void pop() {
        System.out.println(redisTemplate.opsForList().leftPop(key));
        System.out.println(redisTemplate.opsForList().rightPop(key));
    }

    @Test
    public void insert() {
        redisTemplate.getConnectionFactory().getConnection().lInsert(key.getBytes(), RedisListCommands.Position.BEFORE, "3".getBytes(), "before_3".getBytes());
    }


}

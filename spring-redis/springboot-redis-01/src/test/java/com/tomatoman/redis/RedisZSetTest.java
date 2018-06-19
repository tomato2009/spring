package com.tomatoman.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisZSetTest {
    @Resource
    private RedisTemplate redisTemplate;

    String key1 = "zset1";
    String key2 = "zset2";

    /**
     * ／／相当于lpush 把多个价值从左插入链表
     */
    @Test
    public void addzSet() {
        //Spring 提供接口TypedTuple 操作有序集合
        Set<ZSetOperations.TypedTuple> set1 =new HashSet<ZSetOperations.TypedTuple>();
        Set<ZSetOperations.TypedTuple> set2 = new HashSet<ZSetOperations.TypedTuple>();
        int j = 9;
        for (int i=1; i<=9; i++) {
            j--;
            //计算分数和值
            Double scorel = Double.valueOf(i);
            String valuel = "x" + i;
            Double score2 = Double.valueOf(j);
            String value2 = j % 2 == 1 ? "Y" + j : "x" + j;
            //使用Spri 呵提供的默认TypedTuple-DefaultTypedTuple
            ZSetOperations.TypedTuple typedTuplel = new DefaultTypedTuple(valuel, scorel);
            set1.add(typedTuplel);
            ZSetOperations.TypedTuple typedTuple2 = new DefaultTypedTuple(value2, score2);
            set2.add(typedTuple2);
            //将元素插入有序集合zsetl
            redisTemplate.opsForZSet().add(key1, set1);
            redisTemplate.opsForZSet().add(key2, set2);
        }
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

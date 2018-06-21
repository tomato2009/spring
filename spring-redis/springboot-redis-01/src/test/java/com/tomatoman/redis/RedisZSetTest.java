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
public class RedisZSetTest {
    @Resource
    private RedisTemplate redisTemplate;

    String key1 = "zset1";
    String key2 = "zset2";
    String key3 = "inter_zset";

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
     * ／／计分数为score ，那么下面的方法就是求3<=score<=6 的元素
     */
    @Test
    public void members() {
        System.out.println(redisTemplate.opsForZSet().count(key1, 3, 6));
    }

    /**
     * ／／从下标一开始截驭5 个元素，但是不返回分数， 每一个元素是Str 工ng
     */
    @Test
    public void range() {
        Set rangeSet = redisTemplate.opsForZSet().range(key1, 3, 6);
        System.out.println(rangeSet);
    }

    /**
     * ／／截取集合所有元素，并且对集合按分数排序，并返回分数， 每一个元素是TypedTuple
     */
    @Test
    public void rangeWithScore() {
        Set<ZSetOperations.TypedTuple> rangeSet = redisTemplate.opsForZSet().rangeWithScores(key1, 3, 6);
        for (ZSetOperations.TypedTuple typedTuple : rangeSet) {
            System.out.println(typedTuple.getValue() +" " +typedTuple.getScore());
        }

    }

    /**
     * ／／将zsetl 和zset2 两个集合的交集放入集合inter zset
     */
    @Test
    public void intersectAndStore() {
        redisTemplate.opsForZSet().intersectAndStore(key1, key2, key3);

        Set<ZSetOperations.TypedTuple> rangeSet = redisTemplate.opsForZSet().rangeWithScores(key3, 0, -1);
        for (ZSetOperations.TypedTuple typedTuple : rangeSet) {
            System.out.println(typedTuple.getValue() +" " +typedTuple.getScore());
        }
    }

    /**
     * ／／区间, 只返回值，不返回分数
     */
    @Test
    public void rangByLex() {
        RedisZSetCommands.Range range = new RedisZSetCommands.Range();
        range.lte("x8");
        range.gte("x1");

        Set rangeSet = redisTemplate.opsForZSet().rangeByLex(key1, range);
        System.out.println(rangeSet);

    }

    /**
     * 区间, 只返回值，不返回分数
     * 限制返回个数
     */
    @Test
    public void rangByLexLimit() {
        RedisZSetCommands.Range range = new RedisZSetCommands.Range();
        range.lte("x8");
        range.gte("x1");

        RedisZSetCommands.Limit limit = new RedisZSetCommands.Limit();
        //限制返回个数
        limit.count(3);
        //限制从第3个开始截取
        limit.offset(2);

        Set rangeSet = redisTemplate.opsForZSet().rangeByLex(key1, range, limit);
        System.out.println(rangeSet);

    }

    /**
     * ／／统计总数
     */
    @Test
    public void zcard() {
        System.out.println(redisTemplate.opsForZSet().size(key1));
        System.out.println(redisTemplate.opsForZSet().zCard(key1));
        System.out.println(redisTemplate.opsForZSet().size(key2));
        System.out.println(redisTemplate.opsForZSet().zCard(key2));
    }

    /**
     * 求排名，以分数的方式排名， 返回值从0开始
     */
    @Test
    public void rank() {
        System.out.println(redisTemplate.opsForZSet().rank(key1, "x3"));
    }

    /**
     * 求排名，以分数的方式排名， 返回值从0开始
     */
    @Test
    public void remove() {
        //删除元索， 返回删除个数
        long size=  redisTemplate.opsForZSet().remove (key1,"x5","x6");
        System.out.println("remove = " + size );
        //按照排行删除从0 开始算起，这里将删除第排名第2 和第3 的元素
        size= redisTemplate.opsForZSet().removeRange (key2, 1 , 2 ) ;
        System.out.println("removeRange = " + size );

        //按照score 分数范围删除从1 到 2
        size = redisTemplate.opsForZSet().removeRangeByScore(key1, 1, 2);
        System.out.println("removeRangeByScore = " + size );
    }

    /**
     * ／／给集合中的一个元素的分数加上11
     */
    @Test
    public void incrementScore() {
        System.out.println(redisTemplate.opsForZSet().incrementScore(key1, "x8", 11));
    }
}
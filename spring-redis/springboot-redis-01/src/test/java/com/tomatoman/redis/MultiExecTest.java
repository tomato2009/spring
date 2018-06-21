package com.tomatoman.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisZSetCommands;
import org.springframework.data.redis.core.*;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MultiExecTest {
    @Resource
    private RedisTemplate redisTemplate;

    String key1 = "multi_exec_1";
    String key2 = "multi_exec_2";

    /**
     * 事务 三个步骤：
     * 开启事务
     * 命令进入队列
     * 执行命令
     */
    @Test
    public void multi() {
        SessionCallback sessionCallback = new SessionCallback() {
            @Override
            public Object execute(RedisOperations redisOperations) throws DataAccessException {
                //开启事务命令，之后的命令就进入队列，不会马上执行
                redisOperations.multi();

                redisOperations.boundValueOps(key1).set("multi_exec_001");
                //注意由于命令只是进入队列，而没有被执行，所以此处采用get 命令，而value 却返回为 null
                String value = (String) redisOperations.boundValueOps(key1).get();

                System.out.println("事务执行过程中， 命令入队列，而没有被执行，所以value 为空：value= " + value );

                //此时list 会保存之前进入队列的所有命令的结果
                List list = redisOperations.exec();
                System.out.println(list);

                //事务结束后， 获取valuel
                value = (String) redisTemplate.opsForValue().get(key1) ;
                return value ;
            }
        };

        //执行Redis 的命令
        String value = (String) redisTemplate.execute(sessionCallback);
        System.out.println(value);
    }


    /**
     * 事务回滚
     *   执行事务命令的时候，在命令入队的时候， Redis 就会
         检测事务的命令是否正确，如果不正确则会产生错误。无论之前和之后的命令都会被事务
         所回滚，就变为什么都没有执行。当命令格式正确，而因为操作数据结构引起的错误，则
         该命令执行出现错误，而其之前和之后的命令都会被正常执行。这点和数据库很不一样，
         这是需要读者注意的地方。对于一些重要的操作，我们必须通过程序去检测数据的正确性，
         以保证Redis 事务的正确执行，避免出现数据不一致的情况。Redis 之所以保持这样简易的
         事务，完全是为了保证移动互联网的核心问题一一性能。
     */
    @Test
    public void multiRollback() {
        SessionCallback sessionCallback = new SessionCallback() {
            @Override
            public Object execute(RedisOperations redisOperations) throws DataAccessException {
                //开启事务命令，之后的命令就进入队列，不会马上执行
                redisOperations.multi();

                redisOperations.boundValueOps(key2).set("multi_exec_001");
                //注意由于命令只是进入队列，而没有被执行，所以此处采用get 命令，而value 却返回为 null
                String value = (String) redisOperations.boundValueOps(key2).get();

                System.out.println("事务执行过程中， 命令入队列，而没有被执行，所以value 为空：value= " + value );

                //异常，不回滚
                redisOperations.boundValueOps(key2).increment(1);

                //此时list 会保存之前进入队列的所有命令的结果
                List list = redisOperations.exec();
                System.out.println(list);

                //事务结束后， 获取valuel
                value = (String) redisTemplate.opsForValue().get(key1) ;
                return value ;
            }
        };

        //执行Redis 的命令
        String value = (String) redisTemplate.execute(sessionCallback);
        System.out.println(value);
    }
}
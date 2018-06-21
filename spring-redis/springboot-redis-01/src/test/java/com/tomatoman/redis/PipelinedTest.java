package com.tomatoman.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PipelinedTest {
    @Resource
    private RedisTemplate redisTemplate;

    String key1 = "pipelined_1";

    /**
     * 事务 三个步骤：
     * 开启事务
     * 命令进入队列
     * 执行命令
     */
    @Test
    public void pipelined() {
        SessionCallback sessionCallback = new SessionCallback() {
            @Override
            public Object execute(RedisOperations ops) throws DataAccessException {
                for (int i = 0; i < 100000; i++) {
                    int j = i + 1;
                    ops.boundValueOps("pipeline keymm " + j).set("pipeline value" +
                            ops.boundValueOps("pipeline_key" + j).get());

                }
                return null;
            }
        };

        //执行Redis 的命令
        long start = System.currentTimeMillis();
        redisTemplate.executePipelined(sessionCallback);
        System.out.println("time = " + (System.currentTimeMillis() - start));
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
    public void noPipelined() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            int j = i + 1;
            redisTemplate.boundValueOps("pipeline keydd " + j).set("pipeline value" +
                    redisTemplate.boundValueOps("pipeline_key" + j).get());

        }

        System.out.println("time = " + (System.currentTimeMillis() - start));
    }
}
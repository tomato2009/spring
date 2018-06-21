package com.tomatoman.redis;

import com.tomatoman.redis.entity.Role;
import com.tomatoman.redis.service.RoleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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
public class MyBatisRedisTest {
    @Autowired
    private RoleService roleService;

    @Test
    public void rank() {
        Role role = roleService.selectByPrimaryKey(1);
        System.out.println(role);
    }
}
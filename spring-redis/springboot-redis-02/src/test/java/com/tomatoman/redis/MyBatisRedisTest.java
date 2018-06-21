package com.tomatoman.redis;

import com.tomatoman.redis.entity.RedPacket;
import com.tomatoman.redis.entity.Role;
import com.tomatoman.redis.service.RedPacketService;
import com.tomatoman.redis.service.RedisRedPacketService;
import com.tomatoman.redis.service.RoleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyBatisRedisTest {
    @Autowired
    private RoleService roleService;

    @Autowired
    RedPacketService redPacketService;

    @Test
    public void role() {
        Role role = roleService.selectByPrimaryKey(1);
        System.out.println(role);
    }

    @Test
    public void redpacket() {
        RedPacket redPacket = redPacketService.getRedPacket((long) 1);
        System.out.println(redPacket);
    }
}
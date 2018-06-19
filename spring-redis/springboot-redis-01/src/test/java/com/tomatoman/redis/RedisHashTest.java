package com.tomatoman.redis;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.annotation.Resources;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisHashTest {
    @Resource
    private RedisTemplate<String, ?> redisTemplate;

    String key = "role_02";

    /**
     * ／／相当于hmset 命令
     */
    @Test
    public void setMap() {
        Map<String, String> map = new HashMap<String, String>(3);
        map.put("id", "002");
        map.put("roleName", "role_name_002");
        map.put("note", "note_002");

        redisTemplate.opsForHash().putAll(key, map);
    }

    @Test
    public void redisMap() {
        redisTemplate.opsForHash().put(key, "age", "18");
        //／／相当于hincrby 命令
        redisTemplate.opsForHash().increment(key, "age", 3);

        redisTemplate.opsForHash().increment(key, "age", 0.87);
    }

    @Test
    public void redisMapHasKey() {
        //／／相当于hexists key filed 命令
        Assert.assertEquals(redisTemplate.opsForHash().hasKey(key, "age"), true);
        Assert.assertEquals(redisTemplate.opsForHash().hasKey(key, "addr"), false   );
    }

    /**
     * ／／相当于hgetall 命令
     */
    @Test
    public void redisMapEntries() {
        Map<Object, Object> entries = redisTemplate.opsForHash().entries(key);
        System.out.println(entries.toString());
    }

    /**
     * ／／相当于hvals 命令
     */
    @Test
    public void redisMapValues() {
        List<Object> values = redisTemplate.opsForHash().values(key);
        System.out.println(values.toString());
    }

    /**
     * ／／相当于hkeys 命令
     */
    @Test
    public void redisMapKeys() {
        Set<Object> keys = redisTemplate.opsForHash().keys(key);
        System.out.println(keys.toString());
    }


    /**
     * ／／相当于hkeys 命令
     */
    @Test
    public void redisMapMutiGet() {
        List fieldList =new ArrayList<String>() ;
        fieldList.add ("roleName");
        fieldList . add ("note") ;

        List values = redisTemplate.opsForHash().multiGet(key, fieldList);
        System.out.println(values.toString());
    }
}

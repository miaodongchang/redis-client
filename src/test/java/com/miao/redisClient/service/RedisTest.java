package com.miao.redisClient.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RedisTest {
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private IRedisService redisService;

    @Test
    public void set() {
        redisTemplate.opsForValue().set("test2","jedis-cli-test");
    }

    @Test
    public void get() {
        Object test = redisTemplate.opsForValue().get("test2");
        System.out.println(test.toString());
    }

    @Test
    public void hSet(){
        redisService.hSet("stu", "name", "lisan");
    }

    @Test
    public void hGet(){
        String s = redisService.hGet("stu", "name");
        System.out.println(s);
    }
}
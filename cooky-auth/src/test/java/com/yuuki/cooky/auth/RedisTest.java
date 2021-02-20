package com.yuuki.cooky.auth;

import com.alibaba.fastjson.JSON;
import com.yuuki.starter.redis.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class RedisTest {

    @Autowired
    private RedisUtils redisUtils;

    @Test
    public void testSet(){
//        redisUtils.set("test","xxxxx");
//        String test = (String) redisUtils.get("test");
//        log.error("{}", test);
        redisUtils.addValueForList("test","xxx");
    }

    @Test
    public void testGet(){
        List<String> test = redisUtils.subList("test", 0L, 1L);
        log.error("{}", JSON.toJSONString(test));

    }

}

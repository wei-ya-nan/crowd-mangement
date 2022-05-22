package com.wyn.crowd;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author wei-ya-nan
 * @version 1.0
 * @date 2022/5/6
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {

//    private Logger logger = LoggerFactory.getLogger(RedisTest.class);

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void test01(){
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        operations.set("hello", "world");


    }

}

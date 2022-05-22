package com.wyn.crowd.handler;

import com.wyn.crowd.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author wei-ya-nan
 * @version 1.0
 * @date 2022/5/6
 */
@RestController
public class RedisHandler {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @RequestMapping("/set/redis/key/value/remote")
    public ResultEntity<String> setRedisKeyValueRemote(@RequestParam("key") String key,
                                                       @RequestParam("value") String value){
        try {
            ValueOperations<String, String> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            return ResultEntity.successWithData(value);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultEntity.failed(e.getMessage());
        }
    }



    @RequestMapping("/set/redis/key/value/remote/with/timeout")
    ResultEntity<String> setRedisKeyValueRemoteWithTimeout(@RequestParam("key") String key,
                                                           @RequestParam("value") String value,
                                                           @RequestParam("time") long time,
                                                           @RequestParam("timeUnit") TimeUnit timeUnit){
        try {
            ValueOperations<String, String> operations = redisTemplate.opsForValue();
            operations.set(key, value,time,timeUnit);
            return ResultEntity.successWithData(value);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultEntity.failed(e.getMessage());
        }
    }


    @RequestMapping("/remove/redis/key/remote")
    ResultEntity<String> removeRedisKeyRemote(@RequestParam("key") String key){
        try {
            redisTemplate.delete(key);
            return ResultEntity.successWithData();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultEntity.failed(e.getMessage());
        }
    }


    @RequestMapping("/get/redis/string/value/by/key")
    ResultEntity<String> getRedisStringValueByKeyRemote(@RequestParam("key") String key) {
        String s = null;
        try {
            ValueOperations<String, String> operations = redisTemplate.opsForValue();
            s = operations.get(key);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultEntity.failed(e.getMessage());
        }
        return ResultEntity.successWithData(s);
    }



}
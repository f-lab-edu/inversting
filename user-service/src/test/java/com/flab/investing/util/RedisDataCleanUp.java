package com.flab.investing.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class RedisDataCleanUp {

    @Autowired
    private RedisTemplate redisTemplate;

    @Transactional
    public void execute() {
        Set keys = redisTemplate.keys("*");
        redisTemplate.delete(keys);
    }


}

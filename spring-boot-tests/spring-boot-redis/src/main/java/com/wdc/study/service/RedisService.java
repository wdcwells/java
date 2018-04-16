package com.wdc.study.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * Created by wangdachong on 2017/5/21.
 */
@Service
public class RedisService {
    @Autowired private RedisTemplate<String, Object> redisTemplate;

    public void set(String key, Object value, long timeout, TimeUnit unit) {
        if (null != key && key.trim().length() > 0) {
            redisTemplate.opsForValue()
                    .set(key, value, timeout, unit);
        }
    }

    public Object get(String key) {
        if (null != key && key.trim().length() > 0) {
            return redisTemplate.opsForValue()
                    .get(key);
        } else {
            return null;
        }
    }
}

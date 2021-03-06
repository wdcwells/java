package com.wdc.study.controller;

import com.wdc.study.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by wangdachong on 2017/5/19.
 */
@RestController
@RequestMapping("/")
public class HomeController {

    @Autowired private RedisService redisService;

    @RequestMapping(value = {"", "str"})
    public String home() {
        redisService.set("home_cache_str", "Hello World!", 1, TimeUnit.MINUTES);
        Map<String, Object> map = new HashMap();
        redisService.set("home_cache_map_empty", map, 1, TimeUnit.MINUTES);
        map.put("test_str", "test_str");
        map.put("test_object", new HelloObject("test_object1"));
        redisService.set("home_cache_map_content", map, 1, TimeUnit.MINUTES);
        return "Hello World!";
    }

    /**
     * cache with key generated by spEL
     * @param helloObject
     * @return
     */
    @RequestMapping("json1")
    @Cacheable(value = "Hello", key = "#root.targetClass.name + '.' + #root.methodName + ':' + #a0.name")
    public HelloObject helloObject(HelloObject helloObject) {
        return helloObject;
    }

    /**
     * cache with key generated by custom keyGenerator
     * @param name
     * @return
     */
    @RequestMapping("json2")
    @Cacheable(value = "Hello", keyGenerator = "customKeyGenerator")
    public HelloObject helloObject(String name) {
        return new HelloObject(name);
    }

    @RequestMapping("cache")
    public Object getCache(String key) {
        return redisService.get(key);
    }

    /**
     * inner class should be static to be deserialize
     */
    static class HelloObject {
        private String name;

        public HelloObject() {
        }

        public HelloObject(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}

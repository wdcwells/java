package com.wdc.test.controller;

import com.wdc.test.domain.HelloWorld;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by wangdachong on 2017/2/8.
 */
@RestController
@RequestMapping("hello")
public class HelloController {
    private static AtomicInteger atomicInteger = new AtomicInteger();

    @RequestMapping(method = RequestMethod.GET)
    public HelloWorld get(){
        return new HelloWorld(atomicInteger.getAndIncrement(), "hello world");
    }
}

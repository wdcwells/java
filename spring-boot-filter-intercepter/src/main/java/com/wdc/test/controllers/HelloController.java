package com.wdc.test.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("hello")
public class HelloController {
    @GetMapping("txt")
    String getHelloMsg() {
        return "hello";
    }

    @GetMapping("json")
    Map<String, String> getHelloJson() {
        return Collections.singletonMap("key", "value");
    }
 }

package com.wdc.test.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static com.wdc.test.config.SecurityConfig.USER_TOKEN_MAP;

/**
 * Created by wangdachong on 2017/3/10.
 */
@RestController
@RequestMapping("super")
public class SuperController {
    @RequestMapping("tokens")
    public Map<Integer, String> tokens() {
        return USER_TOKEN_MAP;
    }
}

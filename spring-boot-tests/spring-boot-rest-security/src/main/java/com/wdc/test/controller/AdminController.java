package com.wdc.test.controller;

import com.wdc.test.entity.UserEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Collection;

import static com.wdc.test.config.SecurityConfig.USER_DB_MAP;

/**
 * Created by wangdachong on 2017/3/10.
 */
@RestController
@RequestMapping("admin")
public class AdminController {

    @RequestMapping("users")
    public Collection<UserEntity> findUsers(){
        return USER_DB_MAP.values();
    }
}

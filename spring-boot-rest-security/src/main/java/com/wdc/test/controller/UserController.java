package com.wdc.test.controller;

import com.wdc.test.entity.UserEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wangdachong on 2017/3/9.
 */
@RestController
@RequestMapping("user")
public class UserController {

    @RequestMapping("profile")
    public Object get(@AuthenticationPrincipal UserEntity userEntity) {
        Assert.notNull(userEntity, "您的登录有效期已过，请重新登录");
        return userEntity;
    }
}

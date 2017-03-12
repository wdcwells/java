package com.wdc.test.controller;

import com.wdc.test.dto.LoginResponse;
import com.wdc.test.entity.UserEntity;
import com.wdc.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.wdc.test.config.SecurityConfig.USER_LOGIN_MAP;
import static com.wdc.test.config.SecurityConfig.USER_TOKEN_MAP;

/**
 * Created by wangdachong on 2017/3/9.
 */
@RestController
@RequestMapping("login")
public class LoginController {

    private UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public LoginResponse login(Integer uId) throws IOException {
        LoginResponse response = new LoginResponse();
        UserEntity dbUser = userService.findById(uId);
        if (null == dbUser) {
            dbUser = new UserEntity(uId, "user" + uId, "user");
            userService.save(dbUser);
        }
        response.setUser(dbUser);
        String token = USER_TOKEN_MAP.get(uId);
        if (null == token) {
            token = "tokenOf" + uId;
            USER_TOKEN_MAP.put(uId, token);
            USER_LOGIN_MAP.put(token, response.getUser());
        }
        response.setToken(token);
        return response;
    }
}

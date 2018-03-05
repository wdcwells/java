package com.wdc.test.dtos;

import com.wdc.test.entity.UserEntity;

/**
 * Created by wangdachong on 2017/3/9.
 */
public class LoginResponse {
    private UserEntity user;
    private String token;

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

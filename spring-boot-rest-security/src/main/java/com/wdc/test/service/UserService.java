package com.wdc.test.service;

import com.wdc.test.entity.UserEntity;

/**
 * Created by wangdachong on 2017/3/9.
 */
public interface UserService {
    UserEntity findById(Integer uId);
    UserEntity findByToken(String token);
    void save(UserEntity dbUser);
}

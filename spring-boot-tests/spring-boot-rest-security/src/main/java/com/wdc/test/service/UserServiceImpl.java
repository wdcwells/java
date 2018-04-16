package com.wdc.test.service;

import com.wdc.test.entity.UserEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Map;

import static com.wdc.test.config.SecurityConfig.USER_DB_MAP;
import static com.wdc.test.config.SecurityConfig.USER_LOGIN_MAP;

/**
 * Created by wangdachong on 2017/3/9.
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Override
    public UserEntity findById(Integer uId) {
//        if (Arrays.binarySearch(Arrays.stream(USER_DB_MAP.keySet().toArray()).sorted().toArray(), uId) < 0)
//            USER_DB_MAP.put(uId + "", new UserEntity(uId, "user" + uId, "user"));
        Assert.notNull(uId, "用户Id不能为空");
        return USER_DB_MAP.get(uId);
    }

    @Override
    public UserEntity findByToken(String token) {
        Assert.notNull(token, "token不能为空");
        return USER_LOGIN_MAP.get(token);
    }

    @Override
    public void save(UserEntity dbUser) {
        Assert.notNull(dbUser, "用户不能为空");
        USER_DB_MAP.put(dbUser.getuId(), dbUser);
    }
}

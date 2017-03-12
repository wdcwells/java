package com.wdc.test.config;

import org.springframework.security.core.Authentication;

/**
 * Created by wangdachong on 2017/3/12.
 */
public interface MyAuthentication extends Authentication {
    String getToken();
}

package com.wdc.test.config.shiro;

import org.apache.shiro.authc.AuthenticationToken;

public interface SimpleStringAuthenticationToken extends AuthenticationToken {
    String getStringToken();

    default Object getPrincipal() {
        return null;
    }

    default Object getCredentials() {
        return null;
    }
}

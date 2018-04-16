package com.wdc.test.config;

import com.wdc.test.entity.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Collections;

/**
 * Created by wangdachong on 2017/3/12.
 */
public class MyTokenAuthentication implements MyAuthentication {
    private final String token;
    private UserEntity user;
    private boolean authenticated = false;

    public MyTokenAuthentication(String token) {
        this.token = token;
    }

    public MyTokenAuthentication(String token, UserEntity user) {
        this.token = token;
        this.user = user;
    }

    @Override
    public String getToken() {
        return token;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(this.user.getType()));
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return this.user;
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.authenticated = true;
    }

    @Override
    public String getName() {
        return this.user.getName();
    }
}

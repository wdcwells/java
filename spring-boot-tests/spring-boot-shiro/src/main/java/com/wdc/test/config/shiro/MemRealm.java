package com.wdc.test.config.shiro;

public class MemRealm {


    public static void main(String[] args) {

        new SimpleStringAuthenticationToken() {
            @Override
            public String getStringToken() {
                return null;
            }
        };
    }
}

package com.wdc.test.domain;

import java.io.Serializable;

/**
 * Created by wangdachong on 2017/2/8.
 */
public class HelloWorld implements Serializable{
    private int count;
    private String msg;

    public HelloWorld() {
    }

    public HelloWorld(int count, String msg) {
        this.count = count;
        this.msg = msg;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

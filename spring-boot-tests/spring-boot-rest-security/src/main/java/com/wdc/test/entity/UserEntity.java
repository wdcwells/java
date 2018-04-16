package com.wdc.test.entity;

/**
 * Created by wangdachong on 2017/3/9.
 */
public class UserEntity {
    private Integer uId;
    private String name;
    private String type;

    public UserEntity() {
    }

    public UserEntity(Integer uId, String name, String type) {
        this.uId = uId;
        this.name = name;
        this.type = type;
    }

    public Integer getuId() {
        return uId;
    }

    public void setuId(Integer uId) {
        this.uId = uId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

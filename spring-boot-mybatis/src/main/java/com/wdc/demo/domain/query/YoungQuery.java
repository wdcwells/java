package com.wdc.demo.domain.query;

/**
 * Created by wangdachong on 2017/4/15.
 */
public class YoungQuery {
    private String name;
    private Integer age;
    private String name_like;
    private Integer age_start;
    private Integer age_end;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName_like() {
        return name_like;
    }

    public void setName_like(String name_like) {
        this.name_like = name_like;
    }

    public Integer getAge_start() {
        return age_start;
    }

    public void setAge_start(Integer age_start) {
        this.age_start = age_start;
    }

    public Integer getAge_end() {
        return age_end;
    }

    public void setAge_end(Integer age_end) {
        this.age_end = age_end;
    }
}

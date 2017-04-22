package com.wdc.demo.domain.model;

import java.io.Serializable;

/**
 * Created by wangdachong on 2017/4/15.
 */
public class Lovers implements Serializable{
    private String id;
    private String name;
    private Integer boy_id;
    private Integer girl_id;
    private String create_time;
    private String update_time;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBoy_id() {
        return boy_id;
    }

    public void setBoy_id(Integer boy_id) {
        this.boy_id = boy_id;
    }

    public Integer getGirl_id() {
        return girl_id;
    }

    public void setGirl_id(Integer girl_id) {
        this.girl_id = girl_id;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    @Override
    public String toString() {
        return "Lovers{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", boy_id=" + boy_id +
                ", girl_id=" + girl_id +
                ", create_time='" + create_time + '\'' +
                ", update_time='" + update_time + '\'' +
                '}';
    }
}

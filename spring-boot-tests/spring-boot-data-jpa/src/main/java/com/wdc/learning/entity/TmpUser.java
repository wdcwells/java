package com.wdc.learning.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author wdc
 * @date 2018/5/16
 */
@Entity
@Table(name = "tmp_user")
@Data
public class TmpUser {
    @Id
    private String userId;
    private String mobile;
    private String idCard;

    @Override
    public String toString() {
        return "{" +
                "userId='" + userId + '\'' +
                ", mobile='" + mobile + '\'' +
                ", idCard='" + idCard + '\'' +
                '}';
    }
}

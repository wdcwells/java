package com.weiyan.cardloan.dubbo.demo;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author : wyx
 * @description :  please add description.
 * @date : create in 2018/3/27  下午2:31
 * @modified by :
 */
@AllArgsConstructor
@Getter
public enum StatusCodeEnum {

    ok(0,"正常"),
    UNKNOWN_ERROR(32000,"未知异常"),;
    int code;
    String msg;
}

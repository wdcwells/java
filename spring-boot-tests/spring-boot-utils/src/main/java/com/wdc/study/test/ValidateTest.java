package com.wdc.study.test;

import com.wdc.study.utils.ValidateUtil;

import javax.validation.constraints.Pattern;

/**
 * @author wdc
 * @date 2018/9/12
 */
public class ValidateTest {
    @Pattern(regexp = "[a-zA-Z0-9_-]{4,16}", message = "用户名须4到16位（字母，数字，下划线，减号）")
    private String userName;

    public static void main(String[] args) {
        ValidateTest obj = new ValidateTest();
        obj.userName = "王";
        ValidateUtil.validate(obj);
    }
}

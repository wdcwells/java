package com.wdc.learnning.check;

import java.util.regex.Pattern;

public class ValidateUtil {
    public static final String MOBILE_PHONE_PATTERN = "^1[0-9]{10}$";
    public static final String PASSWORD_PATTERN = "[\\da-zA-Z]{8,20}";
    public static void main(String[] args) {
//        boolean success = checkMobilePhone("12222222220");
        boolean success = checkLoginPassword("122222212az111A11111");
        System.out.println(success);
    }

    /**
     * 密码：8-20位字母、数字组合
     * @param password 密码（明文）
     * @return true 校验成功
     *          false 校验失败
     */
    public static boolean checkLoginPassword(String password) {
        if (null == password || password.trim().length() == 0) return false;
        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        return pattern.matcher(password).matches();
    }

    /**
     * 手机号：1打头11位数字
     * @param phone 手机号
     * @return true 校验成功
     *          false 校验失败
     */
    public static boolean checkMobilePhone(String phone) {
        if (null == phone || phone.trim().length() == 0) return false;
        Pattern pattern = Pattern.compile(MOBILE_PHONE_PATTERN);
        return pattern.matcher(phone).matches();
    }
}

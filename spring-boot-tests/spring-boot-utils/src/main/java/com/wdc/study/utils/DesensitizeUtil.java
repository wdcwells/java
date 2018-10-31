package com.wdc.study.utils;

import java.util.Collections;

/**
 * 脱敏处理
 */
public class DesensitizeUtil {
    private static final String DEFAULT_REPLACE_CHAR_UNIT = "*";

    /**
     * @param mobile 手机号
     * @return
     */
    public static String mobile(String mobile) {
        return desensitize(mobile, 3, 4, DEFAULT_REPLACE_CHAR_UNIT);
    }

    /**
     * @param idCard 身份证号
     * @return
     */
    public static String idCard(String idCard) {
        return desensitize(idCard, 6, 4, DEFAULT_REPLACE_CHAR_UNIT);
    }

    /**
     * @param content 内容
     * @param lRetain 左侧保留数量
     * @param rRetain 右侧保留数量
     * @param replaceChar 替换字符
     * @return
     */
    private static String desensitize(String content, int lRetain, int rRetain, String replaceChar) {
        return content.replaceAll("(.{" + lRetain +"}).*(.{" + rRetain + "})",
                "$1" + String.join("", Collections.nCopies(content.length() - lRetain - rRetain, replaceChar)) + "$2");
    }

}

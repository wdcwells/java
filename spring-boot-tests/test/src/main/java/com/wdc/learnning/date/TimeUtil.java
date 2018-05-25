package com.wdc.learnning.date;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author wdc
 * @date 2018/4/11
 */
public class TimeUtil {
    private static final DateTimeFormatter DEFAULT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static String format(LocalDateTime localDateTime) {
        return format(DEFAULT_FORMAT, localDateTime);
    }

    public static String format(String pattern, LocalDateTime localDateTime) {
        if (null == localDateTime) return null;
        DateTimeFormatter formatter = null;
        try {
            formatter = DateTimeFormatter.ofPattern(pattern);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return format(formatter, localDateTime);
    }

    public static String format(DateTimeFormatter formatter, LocalDateTime localDateTime) {
        if (null == localDateTime) return null;
        if (null == formatter) formatter = DEFAULT_FORMAT;
        return localDateTime.format(formatter);
    }

    public static void main(String[] args) {
        System.out.println(format(LocalDateTime.now()));
    }
}

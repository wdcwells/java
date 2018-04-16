package com.wdc.learnning.regular;

import java.util.regex.Pattern;

public class RegularTest {
    private static final Pattern phonePattern = Pattern.compile("[^\u4e00-\u9fa5]{0,5}");
    private static final Pattern creditCardPattern = Pattern.compile("[1-9]");

    public static void main(String[] args) {
        System.out.println(creditCardPattern.matcher("123").matches());
        System.out.println(creditCardPattern.matcher("9").matches());
        System.out.println(creditCardPattern.matcher("").matches());
//        System.out.println(Pattern.compile("[^\u4e00-\u9fa5]*").matcher("中").matches());
//        System.out.println(phonePattern.matcher("").matches());
//        System.out.println(phonePattern.matcher("1").matches());
//        System.out.println(phonePattern.matcher("232").matches());
//        System.out.println(phonePattern.matcher("666666").matches());
//        System.out.println(phonePattern.matcher("中66").matches());
    }
}

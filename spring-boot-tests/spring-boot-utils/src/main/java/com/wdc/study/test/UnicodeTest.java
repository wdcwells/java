package com.wdc.study.test;

/**
 * @author wdc
 * @date 2018/7/12
 */
public class UnicodeTest {
    public static void main(String[] args) {
        System.out.println("\\u2122=\u2122");
        //error for input single"\"
        System.out.println("\\u005c");

        System.out.print("abc\nd");
        System.out.print("abc\rd");

        System.out.print("a\\u000D \\u000A");//CR LF
        System.out.print("b\\u000D");//CR
        System.out.print("c\\u000A");//LF
    }
}

package com.wdc.study.test;

import java.util.Arrays;

/**
 * @author wdc
 * @date 2018/7/27
 */
public enum EnumTest {
    e1,e2,e3
    ;

    public static void main(String[] args) {
        twoWaysForValues();
    }

    private static void twoWaysForValues() {
        Arrays.stream(EnumTest.class.getEnumConstants()).forEach(System.out::print);
        System.out.println();
        Arrays.stream(EnumTest.values()).forEach(System.out::print);
    }
}

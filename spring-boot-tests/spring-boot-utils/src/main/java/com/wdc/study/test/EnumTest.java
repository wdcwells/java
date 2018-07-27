package com.wdc.study.test;

import java.util.Arrays;

/**
 * @author wdc
 * @date 2018/7/27
 */
public enum EnumTest {
    e1,e2
    ,e3 {
        void print1() {//never can be accessed
            System.out.println("Instance methods declared in these class bodies may be invoked outside the enclosing enum type only if they override accessible methods in the enclosing enum type");
        }
        void print() {
            System.out.println("inner");
        }
    }
    ;
    void print() {
        System.out.println("outer");
    }

    public static void main(String[] args) {
        twoWaysForValues();
    }

    private static void twoWaysForValues() {
        Arrays.stream(EnumTest.class.getEnumConstants()).forEach(System.out::print);
        System.out.println();
        Arrays.stream(EnumTest.values()).forEach(System.out::print);
    }
}

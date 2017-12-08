package com.wdc.learnning.reflect;

import java.util.Arrays;

public class MethodDesc {
    public static void m1() {
        System.out.println("m1");
    }

    public static void main(String[] args) {
        Arrays.stream(MethodDesc.class.getDeclaredMethods()).forEach(System.out::println);
        System.out.println("------------------");
        Arrays.stream(MethodDesc.class.getMethods()).forEach(System.out::println);
    }
}

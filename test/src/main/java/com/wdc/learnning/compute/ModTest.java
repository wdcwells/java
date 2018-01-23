package com.wdc.learnning.compute;

import java.util.stream.Stream;

public class ModTest {
    public static void main(String[] args) {
        System.out.println(getSteps(1000, 500));
        System.out.println(getSteps(1000, 1000));
        System.out.println(getSteps(1000, 2000));
        System.out.println(getSteps(1000, 2001));

        Stream.iterate(0, n -> n+1).limit(1).parallel().forEach(n -> System.out.println(n));
    }
    private static int getSteps(int maxQuerySize, int size) {
        int i = size / maxQuerySize;
        if (i > 0) return i + size % maxQuerySize;
        else return i+1;
    }
}

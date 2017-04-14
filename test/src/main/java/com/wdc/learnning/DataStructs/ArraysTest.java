package com.wdc.learnning.DataStructs;

import org.junit.Test;

import java.util.Arrays;

/**
 * Created by wangdachong on 2017/4/14.
 */
public class ArraysTest {
    @Test
    public void testInitValues() {
        Object[] refArrayS = new Object[2];//引用数据类型
        Arrays.stream(refArrayS).forEach(o -> System.out.println(o));
        String[] strings = new String[2];//串
        Arrays.stream(strings).forEach(s -> System.out.println(s));
        int[] ints = new int[2];//整型
        Arrays.stream(ints).forEach(value -> System.out.println(value));
        double[] doubles = new double[2];
        Arrays.stream(doubles).forEach(value -> System.out.println(value));
    }
}

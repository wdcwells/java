package com.wdc.learnning.datastructs;

import org.junit.Test;

import java.util.Arrays;

/**
 * Created by wangdachong on 2017/4/14.
 */
public class ArraysTest {
    @Test
    public void testInitValues() {
        Object[] refArrayS = new Object[2];//引用数据类型
        Arrays.stream(refArrayS).forEach(System.out::println);
        String[] strings = new String[2];//串
        Arrays.stream(strings).forEach(System.out::println);
        int[] ints = new int[2];//整型
        Arrays.stream(ints).forEach(System.out::println);
        double[] doubles = new double[2];
        Arrays.stream(doubles).forEach(System.out::println);
    }

    @Test
    public void testNull(){
        Integer i = null;
        System.out.println(new Integer(1).equals(i));
    }
}

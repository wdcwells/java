package com.wdc.learnning.datastructs;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    @Test
    public void testListRemove(){
        List<String> list = new ArrayList<>(Arrays.asList("java","test","java","nu","java"));
//        for (int i = list.size() - 1; i >=0 ; i--) {
//            if ("java".equals(list.get(i))) list.remove(i);
//        }

        for (int i = 0; i < list.size(); i++) {
            if ("java".equals(list.get(i))) list.remove(i);
        }
        list.forEach(System.out::println);
    }
}

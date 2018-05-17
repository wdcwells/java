package com.wdc.learnning.compute;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Created by wangdachong on 2017/8/1.
 */
public class ComputeTest {
    @Test
    public void test() {
        System.out.println(300 / 500);

        System.out.println(1000 / 500);

        System.out.println(1000 % 500);

        System.out.println(1003 / 500);

        System.out.println(1003 % 500);

        List<Integer> list = Arrays.asList(1,2,3,4,5);

        List<Integer> subList = list.subList(0, 2);
        List<Integer> subList1 = list.subList(2, 4);
        List<Integer> subList2 = list.subList(0, 5);

        subList.forEach(System.out::print);
        subList1.forEach(System.out::print);
        subList2.forEach(System.out::print);

    }

    @Test
    public void test1() {
        System.out.println(-7 / 2);
        System.out.println(-7 % 2);
        System.out.println(7 % -2);
        System.out.println(7 / -2);

        StringBuilder builder = new StringBuilder();
        builder.append((260965+40421+0) / 100.0);
        builder.append("\n");
        builder.append(260965 / 100.0 + 40421 / 100.0 + 0/100.0);
        System.out.println(builder);
    }
}

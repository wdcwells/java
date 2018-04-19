package com.wdc.learnning.dailypractice;

import org.junit.Test;

public class Tests {

    /**
     * 一球从100米高度自由落下，每次反弹到原高度一半
     * 求：第10次落地时总经过多少米，第10次反弹多高？
     */
    @Test
    public void test1() {
        double initHigh = 100;//初始高度

        double totalLength = 0;//总路程
        double currentHigh = 0;//当前高度

        int beichushu = 1;
        for (int i = 0; i < 10; i++) {
            if (i == 0) {
                totalLength += initHigh;
            } else {
                beichushu *= 2;
                currentHigh = initHigh / beichushu;
                totalLength += currentHigh * 2;
            }

        }
        System.out.println("第10次反弹:" + currentHigh / 2);
        System.out.println("总经过:" + totalLength);

    }

    @Test
    public void test3() {
        System.out.println("setWidth:1".substring(9));
    }
}

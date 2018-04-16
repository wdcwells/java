package com.wdc.learnning.operator;

public class OperatorTest {
    public static void main(String[] args) {
        test1();
    }

    /**
     * 操作符：'|'，当两边操作数的位有一边为1时，结果为1，否则为0。如1100|1010=1110
     */
    private static void test1() {
        System.out.println(1028 | 32323);
        System.out.println(true | false);
        System.out.println(false | true);
        System.out.println(false | false);
        System.out.println(true | true);
    }
}

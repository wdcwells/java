package com.wdc.learnning.datastructs;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 1*1 boolean
 * 1*8 byte
 * 2*8 char short
 * 4*8 int float
 * 8*8 long  double
 */
public class BasicTypes {
    public static void main(String[] args) {
//        printAZaz();
        computeDecimal();
    }

    private static void computeDecimal() {
        System.out.println(BigDecimal.valueOf(1).divide(BigDecimal.valueOf(3), 2, RoundingMode.HALF_UP).doubleValue());
        System.out.println(BigDecimal.valueOf(1013.6).divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP).doubleValue());
    }

    private static void printAZaz() {
        printChar(65, 90, 10);
        printChar(97, 122, 10);
    }

    private static void printChar(int start, int end, int len) {
        if (start > end) return;
        int length = len <=0 ? 10 : len;
        do {
            if ((end - start) % length == 0) {
                System.out.println(start + ":" + (char) start + ",");
            } else {
                System.out.print(start + ":" + (char) start + ",");
            }
        } while (start++ < end);
    }
}

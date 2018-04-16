package com.wdc.learnning.datastructs;

public class BasicTypes {
    public static void main(String[] args) {
        //int char byte boolean short
        //long float double

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

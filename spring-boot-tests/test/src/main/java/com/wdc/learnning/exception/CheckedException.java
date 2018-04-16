package com.wdc.learnning.exception;

public class CheckedException extends Exception {
    private static void m1() throws CheckedException {
        throw new CheckedException();
    }

    public static void main(String[] args) throws CheckedException {
        m1();
    }
}

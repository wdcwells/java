package com.wdc.learnning.methods;

public class ConstructMethod {
    private final int a;
    private int b;

    public ConstructMethod(int a) {
        System.out.println(this.b);
        this.a = a;
    }

    public static void main(String[] args) {
        System.out.println(new ConstructMethod(1).a);
    }
}

package com.wdc.learnning.statics;

public class StaticTest extends StaticParentTest{
    static {
        System.out.println("StaticTest");
    }
    {
        System.out.println("Common StaticTest");
    }

    public StaticTest() {
        System.out.println("StaticTest is constructed");
    }

    public static void main(String[] args) {
        StaticParentTest test = new StaticTest();
        test = new StaticTest();

    }
}
class StaticParentTest {
    static {
        System.out.println("StaticParentTest");
    }
    {
        System.out.println("Common StaticParentTest");
    }

    public StaticParentTest() {
        System.out.println("StaticParentTest is constructed");
    }
}

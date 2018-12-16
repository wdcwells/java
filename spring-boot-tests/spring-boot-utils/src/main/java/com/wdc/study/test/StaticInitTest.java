package com.wdc.study.test;

public class StaticInitTest {
    private static class Parent {
        {
            System.out.println("init parent");
        }
        static {
            System.out.println("static Parent");
        }
    }


    private static class Son extends Parent {
        {
            System.out.println("init Son");
        }
        static {
            System.out.println("static Son");
        }

        public static void main(String[] args) {
            new Son();
        }
    }

    public static void main(String[] args) {
        Son.main(args);
    }
}

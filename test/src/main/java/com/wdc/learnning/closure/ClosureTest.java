package com.wdc.learnning.closure;

import org.junit.Test;

public class ClosureTest {
    private static String name = ClosureTest.class.getName();
    private String test = "test";
    private class Inner{
        private String name = Inner.class.getName();
        public void test(){
            System.out.println(this.name);
            System.out.println(ClosureTest.this.name);
        }
    }
    private static class Inner2{
        private String name = Inner2.class.getName();
        public void test(){
            System.out.println(this.name);
            System.out.println(ClosureTest.name);
        }
    }

    @Test
    public void test(){
        class MethodClass {
            private String name = MethodClass.class.getName();
            public void test() {
                System.out.println(this.name);
                System.out.println(ClosureTest.this.test);
            }
        }
        new MethodClass().test();
    }

    public static void main(String[] args) {
        new ClosureTest().new Inner().test();
        new Inner2().test();//
    }

}

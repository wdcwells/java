package com.wdc.study.test.other;

/**
 * @author wdc
 * @date 2018/7/25
 */
public class Parent {
    int i0;
    int i = 10;
    int i1 = i1s();

    private int i1s() {
        return 20;
    }

    protected String s = "string";
    protected static String ss = "static string";

    public Parent() {
        System.out.println(i0 + "\t" + i + "\t" + i1);//field initialized before constructor execute
    }

    protected void m1() {
        System.out.println("m1");
    }



    public static class SuperClass implements ISuper {
        public int s = 1;
        public SuperClass() {
            print3();
        }
        protected void print3() {
            System.out.println("three");
        }

    }

    public interface ISuper {
        int s = 10;
    }

    public static void main(String[] args) {
        new Parent().new P1().print();
    }

    private class P1 extends SP {
        @Override
        public void print() {
            System.out.println(Parent.super.toString());
            System.out.println(P1.super.toString());
        }
    }

    private class SP {
        protected void print() {}
    }


    public interface IParent {
        int i = 1, ii = test("ii", 2), s = 3;

        static int test(String ii, int i) {
            System.out.println(ii + " is init for value:" + i);
            return i;
        }
    }
}
class TopLevelClassOfCompileUnit {}
class TopLevelClass2OfCompileUnit {}

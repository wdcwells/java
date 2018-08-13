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



    public static class SuperClass {

    }

    public static void main(String[] args) {

    }

    public interface IParent {
        int i = 1, ii = test("ii", 2);

        static int test(String ii, int i) {
            System.out.println(ii + " is init for value:" + i);
            return i;
        }
    }
}
class TopLevelClassOfCompileUnit {}
class TopLevelClass2OfCompileUnit {}

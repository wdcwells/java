package com.wdc.study.test;

import com.wdc.study.test.other.Parent;

/**
 * @author wdc
 * @date 2018/7/25
 */
public class InheritTest {

    static class Inner extends Parent {
        protected String ss;
       void test() {
//           System.out.println(i + s);
       }
       void print() {
           System.out.println(s + "," + ss + "," + Parent.ss);
       }
    }

    public static void main(String[] args) throws ClassNotFoundException {
        new Inner().print();
    }

    class SonClass extends Parent.SuperClass {

    }
}

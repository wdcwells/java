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
        //region hiding test
        new Inner().print();
        //endregion

        //region interface initializer test
        System.out.println(ISon.i);
        System.out.println(IGrandSon.s);
        //endregion
    }

    class SonClass extends Parent.SuperClass {

    }

    interface ISon extends Parent.IParent {
        int s = Parent.IParent.test("s", 3), ss = Parent.IParent.test("ss", 4);
    }

    interface IGrandSon extends ISon {
        int g = Parent.IParent.test("g", 5), gg = Parent.IParent.test("gg", 6);
    }
}

class TopLevelClassOfCompileUnit {}
class TopLevelClass1OfCompileUnit {}

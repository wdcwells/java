package com.wdc.study.test;

/**
 * @author wdc
 * @date 2018/9/26
 */
public class FieldTest {
    {
        System.out.println(this.fi);
        this.fi = 1;
    }
    int fi = 2;
    {
        System.out.println(this.fi);
        this.fi = 3;
    }

    public FieldTest() {
        this(4);
//        fi = 1; final can't be initialized twice
    }

    public FieldTest(int fi) {
        System.out.println(this.fi);
        this.fi = fi;
        System.out.println(this.fi);
    }

    public static void main(String[] args) {
        new FieldTest();
    }


}

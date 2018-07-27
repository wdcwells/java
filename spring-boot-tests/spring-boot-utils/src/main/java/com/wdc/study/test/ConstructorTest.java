package com.wdc.study.test;

/**
 * @author wdc
 * @date 2018/7/27
 */
public class ConstructorTest {
    private int i;
    private int p;

    public ConstructorTest() {
//        this(this.i); an explicit invocation of an alternative constructor should not refer to instance variable or method
        this(10);
    }

    public ConstructorTest(int i) {
        this.p = i;
        this.i = this.im();
    }

    private int im() {
        return 1;
    }
}

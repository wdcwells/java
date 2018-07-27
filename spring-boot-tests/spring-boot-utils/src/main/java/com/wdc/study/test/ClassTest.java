package com.wdc.study.test;

/**
 * @author wdc
 * @date 2018/7/27
 */
public class ClassTest {
    public static void main(String[] args) throws Exception {
        forNameVSdotClass();
    }

    private static void forNameVSdotClass() throws ClassNotFoundException {
        Class<?> forName = Class.forName("com.wdc.study.test.ClassTest");
        Class<ClassTest> dot = ClassTest.class;
        System.out.println(forName == dot);
    }
}

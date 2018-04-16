package com.wdc.learnning.reflect;

import org.junit.Test;

import java.lang.reflect.Field;

/**
 * Created by wangdachong on 2017/7/5.
 */
public class TestReflect {
    @Test
    public void test1() throws NoSuchFieldException, IllegalAccessException {
        Class<TestClass> claz = TestClass.class;
        TestClass obj = new TestClass();
        Field name = claz.getDeclaredField("name");
        name.setAccessible(true);
        name.set(obj, "nihao");
        System.out.println(obj.getName());
    }
}

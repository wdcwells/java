package com.wdc.learnning.classes.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ModTest {
    private ModTest() {
    }

    private static String testM(String str) {
        return "ok" + str;
    }

    public static void main(String[] args) {
        Class<ModTest> modTestClass = ModTest.class;
        Constructor<ModTest> declaredConstructor = null;
        try {
            declaredConstructor = modTestClass.getDeclaredConstructor();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        declaredConstructor.setAccessible(true);
        Method method = null;
        try {
            method = modTestClass.getDeclaredMethod("testM", String.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        method.setAccessible(true);
        Object aa = null;
        try {
            aa = method.invoke(declaredConstructor.newInstance(), " happy new year");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        System.out.println(aa);
    }
}

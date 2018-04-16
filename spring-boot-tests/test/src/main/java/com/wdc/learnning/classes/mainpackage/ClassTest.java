package com.wdc.learnning.classes.mainpackage;

import com.wdc.learnning.classes.otherpackage.OtherPackageClass2;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class ClassTest {
    //私有构造类/final类 不可被继承
//    private static class ExtendOtherPackage extends OtherPackageClass {}
//    private static class ExtendOtherPackage extends OtherPackageClass1{}
    private static class ExtendOtherPackage extends OtherPackageClass2 {}

    public static void main(String[] args) {
        System.out.println(new ExtendOtherPackage());
        System.out.println(new ThisPackageClass2());
        System.out.println(new ThisPackageClass3("test"));
        try {
            //尝试调用私有构造器
            System.out.println(ThisPackagePrivateConstruct.class.newInstance());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        try {
            //反射机制
            ThisPackagePrivateConstruct obj = ThisPackagePrivateConstruct.getInstance();
            ThisPackagePrivateConstruct a ;
            ThisPackagePrivateConstruct b ;
            Constructor<?>[] constructors = obj.getClass().getDeclaredConstructors();
            constructors[0].setAccessible(true);
            constructors[1].setAccessible(true);
            a = (ThisPackagePrivateConstruct) constructors[0].newInstance();
            b = (ThisPackagePrivateConstruct) constructors[1].newInstance("b");
            System.out.println(a);
            System.out.println(b);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }
}

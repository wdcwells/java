package com.wdc.learnning.classes.loader;

public class LoaderClass {
    interface A {void sayHello();}

    public static void main(String[] args) {
        A a = new A() {
            {
                System.out.println("class init");
            }
            @Override
            public void sayHello() {
                System.out.println(getClass());
                System.out.println(getClass().getClassLoader());
            }
        };
        a.sayHello();

        System.out.println(new LoaderClass().getClass());
        System.out.println(new LoaderClass().getClass().getClassLoader());
    }

}

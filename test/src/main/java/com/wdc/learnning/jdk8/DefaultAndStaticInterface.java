package com.wdc.learnning.jdk8;

import com.wdc.learnning.jdk8.another.ProtectedImplInterface;

public interface DefaultAndStaticInterface {

    static void main(String[] args) {
        DefaultAndStaticInterface implClass = new ImplClass();
        implClass.sayHello();
        DefaultAndStaticInterface.sayHi();
//        implClass.sayHi();不能用实现类调用
        implClass.sayByImpl();

        //在jdk1.8之前，如果不期望用户直接使用接口实现类编程，可以将实现类接口访问范围修改为protected，
        //那么就需要在写一个instance的工厂方法。现在可以直接在接口中使用静态方法，就不需要在写工厂类方法了。
        ProtectedImplInterface protectedImplInterface = ProtectedImplInterface.ImplFactory.getImpl1();
        protectedImplInterface.sayHello();
    }

    default void sayHello() {
        System.out.println("hello");
    }

    static void sayHi() {
        System.out.println("hi");
    }

    void sayByImpl();

    class ImplClass implements DefaultAndStaticInterface {

        @Override
        public void sayByImpl() {
            System.out.println("byImpl");
        }
    }
}

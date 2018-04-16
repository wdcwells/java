package com.wdc.learnning.jdk8.another;

public interface ProtectedImplInterface {
    void sayHello();

    /**
     * 在jdk1.8之前，如果不期望用户直接使用接口实现类编程，可以将实现类接口访问范围修改为protected，
     * 那么就需要在写一个instance的工厂方法。现在可以直接在接口中使用静态方法，就不需要在写工厂类方法了。
     */
    class ImplFactory {

        protected static class Impl1 implements ProtectedImplInterface {
            @Override
            public void sayHello() {
                System.out.println("impl1");
            }
        }

        public static ProtectedImplInterface getImpl1() {
            return new Impl1();
        }
    }

    static void main(String[] args) {
        ProtectedImplInterface implInterface = ImplFactory.getImpl1();
        implInterface.sayHello();
    }
}

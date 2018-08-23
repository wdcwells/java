package com.wdc.study.test;

/**
 * @author wdc
 * @date 2018/7/27
 */
public class ClassTest {
    private static ITest memITest = () -> System.out.println("memITest");
    public static void main(String[] args) throws Exception {
//        forNameVSdotClass();
        memITest.print();
        staticPrint();
        ClassTest classTest = new ClassTest();
        classTest.print();
        classTest.local();
    }

    private void print() {
        ((ITest) () -> System.out.println(ClassTest.this.getClass().getName())).print();
    }

    private void local() {

        class LocalTest {
            public void print() {
                System.out.println("LocalTest");
            }
        }

        staticInitLocal(LocalTest.class);
    }

    private static void staticInitLocal(Class<?> localTestClass) {
        System.out.println(localTestClass.getName());
    }

    private static void staticPrint() {
        new ITest() {
            @Override
            public void print() {
                //in static context there is not the enclosing instance
//                System.out.println(ClassTest.this.getClass().getSimpleName());
                System.out.println(this.getClass().getName());
            }
        }.print();
    }

    private static void forNameVSdotClass() throws ClassNotFoundException {
        Class<?> forName = Class.forName("com.wdc.study.test.ClassTest");
        Class<ClassTest> dot = ClassTest.class;
        System.out.println(forName == dot);
    }

    private interface ITest {
        void print();
    }
}

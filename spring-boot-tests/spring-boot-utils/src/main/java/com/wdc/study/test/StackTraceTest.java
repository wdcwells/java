package com.wdc.study.test;

/**
 * @author wdc
 * @date 2018/9/27
 */
public class StackTraceTest {
    public static void main(String[] args) {
        StaticMethods.print();
        StaticMethods.print1();
    }
}

class StaticMethods {
    static void print() {
        // 通过堆栈信息获取调用当前方法的类名和方法名
        String className = "";
        String methodName = "";
        StackTraceElement[] elements = new Throwable().getStackTrace();
        for (int i = 0; i < elements.length; i++){
            if (StaticMethods.class.getName().equals(elements[i].getClassName())){
                // 获取堆栈的下一个元素，就是调用者元素
                // 如果想要获取当前方法所在类的信息，直接读取elements[i]就可以了
                className = elements[i + 1].getClassName();
                methodName = elements[i + 1].getMethodName();
                break;
            }
        }
        System.out.println(className + "." + methodName + "\n");
    }

    static void print1() {
        final StackTraceElement traceElement = Thread.currentThread().getStackTrace()[2];
        System.out.println(traceElement.getClassName() + "." + traceElement.getMethodName() + "\n");
    }
}

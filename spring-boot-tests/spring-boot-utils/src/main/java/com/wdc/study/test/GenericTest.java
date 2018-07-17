package com.wdc.study.test;

/**
 * @author wdc
 * @date 2018/7/16
 */
public class GenericTest<T> //type variable
{
    T t;

    /**
     * generic method
     * @param t
     * @param <M>
     */
    public static <M> void print(M t) {
        System.out.println(t.getClass());
    }

    public static void main(String[] args) {
    }
}


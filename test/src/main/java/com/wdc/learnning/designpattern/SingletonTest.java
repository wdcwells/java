package com.wdc.learnning.designpattern;

public class SingletonTest {

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> System.out.println(MySingleton.getInstance())).start();
        }
    }

}

class MySingleton {
    private static MySingleton mySingleton;

    private MySingleton() {
    }

    public static MySingleton getInstance() {
        synchronized (MySingleton.class) {
            if (null == mySingleton) mySingleton = new MySingleton();
        }
        return mySingleton;
    }

}

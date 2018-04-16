package com.wdc.learnning.thread;

public class OrderedThread {
    boolean a;
    public void write() {
        int i = 256;
        double d = 20;
        a = true;
        System.out.println("write");
    }
    public void read() {
        while (!a) {
            System.out.println("read1");
        }
        System.out.println("read2");
    }

    public static void main(String[] args) throws InterruptedException {
        OrderedThread orderedThread = new OrderedThread();
        Thread t1 = new Thread(() -> orderedThread.read());
        Thread t2 = new Thread(() -> orderedThread.write());
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }
}

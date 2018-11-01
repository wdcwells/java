package com.wdc.study.test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author wdc
 * @date 2018/11/1
 */
public class SyncTest {
    private static final Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch downLatch = new CountDownLatch(2);
        new MyThread("t1", downLatch).start();
        new MyThread("t2", downLatch).start();
        downLatch.await();
    }

    private static class MyThread extends Thread {
        private CountDownLatch downLatch;
        private AtomicLong count = new AtomicLong();
        MyThread(String name, CountDownLatch downLatch) {
            this.downLatch = downLatch;
            setName(name);
        }
        @Override
        public void run() {
            synchronized (lock) {
                long i;
                while ((i = count.getAndAdd(1)) < 3) {
                    try {
                        System.out.println(getName() + ":" + i);
//                        Thread.sleep(500);
                        lock.wait(500);
                    } catch (InterruptedException e) {
                    }
                }
                lock.notifyAll();
                downLatch.countDown();
            }
        }
    }
}

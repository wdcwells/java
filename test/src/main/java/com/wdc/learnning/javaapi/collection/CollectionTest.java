package com.wdc.learnning.javaapi.collection;

import org.junit.Test;

import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by wangdachong on 2017/8/2.
 */
public class CollectionTest {

    public static void main(String[] args) throws InterruptedException {
        //挂了……
//        CollectionTest test = new CollectionTest();
//        for (int i = 0; i < 100; i++) {
//            test.testVectorThreadSafety();
//        }

    }

    /**
     * 集合取交集
     */
    @Test
    public void testListInList() {
        List<Data> list1 = new ArrayList<>(
                Arrays.asList(
                        new Data(),
                        new Data(1, "wdc"),
                        new Data(2, "wqh")
                )
        );
        List<Data> list2 = new ArrayList<>(
                Arrays.asList(
                        new Data(),
                        new Data(1, "wdc")
                )
        );
        list1.retainAll(list2);//取集合交集
        System.out.println("list2 in list1:" + list1);
    }

    @Test
    public void testListEfficiency() {
        List<Integer> arrayList = new ArrayList<>();
        List<Integer> linkedList = new LinkedList<>();

        long arrayListBegin = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            arrayList.add(i);
        }
        long arrayListEnd = System.currentTimeMillis();

        long linkedListBegin = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            linkedList.add(i);
        }
        long linkedListEnd = System.currentTimeMillis();

        System.out.println(String.format("arrayList-add-time:%d \t linkedList-add-time:%d", arrayListEnd - arrayListBegin, linkedListEnd - linkedListBegin));

        long arrayListIndexBegin = System.currentTimeMillis();
        arrayList.indexOf(100000);
        long arrayListIndexEnd = System.currentTimeMillis();
        long linkedListIndexBegin = System.currentTimeMillis();
        linkedList.indexOf(100000);
        long linkedListIndexEnd = System.currentTimeMillis();
        System.out.println(String.format("arrayList-find-time:%d \t linkedList-find-time:%d", arrayListIndexEnd - arrayListIndexBegin, linkedListIndexEnd - linkedListIndexBegin));
    }

    @Test
    public void testListThreadSafety() throws InterruptedException {
        List<Object> arrayList = new ArrayList<>();
        CountDownLatch count = new CountDownLatch(1000);

        for (int i = 0; i < 1000; i++) {
            new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    arrayList.add(j);
                }
                count.countDown();
            }).start();
        }
        count.await();
        System.out.println(arrayList.size());
    }

    @Test
    public void testSynchronizedList() {
        ExecutorService executorService = Executors.newFixedThreadPool(1000);
        List<Object> list = Collections.synchronizedList(new ArrayList<>());
        for (int i = 0; i < 1000; i++) {
            executorService.execute(() -> {
                for (int j = 0; j < 100; j++) {
                    list.add(j);
                }
            });
        }
        System.out.println(list.size());
    }

    @Test
    public void testVectorThreadSafety() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(1000);
        Vector<Object> vector = new Vector<>();
        CountDownLatch count = new CountDownLatch(1000);
        for (int i = 0; i < 1000; i++) {
            executorService.execute(() -> {
                for (int j = 0; j < 100; j++) {
                    vector.add(j);
                }
                count.countDown();
            });
        }
        count.await();
        System.out.println(vector.size());
    }

    private class Data {
        private Integer id;
        private String name;

        public Data() {
        }

        public Data(Integer id, String name) {
            this.id = id;
            this.name = name;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (null == obj) return false;
            Data other = (Data) obj;
            return (this.id == other.id);
        }
    }

    /**
     * foreach 动态数组异常
     */
    @Test
    public void testArrayListForEach(){
        List<Integer> list1 = Arrays.asList(1,2,3);
        List<Integer> list2 = new ArrayList<>(Arrays.asList(1,2,3));
        List<Integer> list3 = new ArrayList<>();
        list3.add(1);
        list3.add(2);
        list3.add(3);

        try {
//            for (int i = 0; i < list1.size(); i++) {
//                list1.add(list1.get(i)*10);
//            }

//            for (Integer i: list1
//                 ) {
//                list1.add(i*10);
//            }

//            list1.forEach(integer -> list1.add(integer*10));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("--------list1 exception--------");
        }
        try {
            for (int i = 0; i < list2.size(); i++) {
                if (i<5) {
                    list2.add(list2.get(i)*10);
                } else {
                    break;
                }
            }

//            for (Integer i: list2
//                 ) {
//                if (i < 20) {
//                    list2.add(i*10);
//                } else {
//                    break;
//                }
//            }

//            list2.forEach(integer -> {
//                if (integer < 20) {
//                    list2.add(integer*10);
//                }
//            });
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("--------list2 exception--------");
        }
        try {
            for (int i = 0; i < list3.size(); i++) {
                if (i < 5) {
                    list3.add(list3.get(i)*10);
                } else {
                    break;
                }
            }

//            for (Integer i: list3
//                 ) {
//                if (i<5) {
//                    list3.add(i*10);
//                } else {
//                    break;
//                }
//            }

//            list3.forEach(integer -> {
//                if (integer < 20) {
//                    list3.add(integer*10);
//                }
//            });
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("--------list3 exception--------");
        }

        System.out.println(Arrays.toString(list1.toArray()));
        System.out.println(Arrays.toString(list2.toArray()));
        System.out.println(Arrays.toString(list3.toArray()));

    }
}

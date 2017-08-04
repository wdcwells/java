package com.wdc.learnning.javaapi.collection;

import org.junit.Test;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by wangdachong on 2017/8/2.
 */
public class CollectionTest {

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
    public void testListThreadSafety() {
        List<Object> arrayList = new ArrayList<>();

        for (int i = 0; i < 1000; i++) {
            new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    arrayList.add(j);
                }
            }).start();
        }
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
    public void testVectorThreadSafety() {
        ExecutorService executorService = Executors.newFixedThreadPool(1000);
        Vector<Object> vector = new Vector<>();
        for (int i = 0; i < 1000; i++) {
            executorService.execute(() -> {
                for (int j = 0; j < 100; j++) {
                    vector.add(j);
                }
            });
        }
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
}

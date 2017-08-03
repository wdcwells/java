package com.wdc.learnning.javaapi.collection;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by wangdachong on 2017/8/2.
 */
public class CollectionTest {

    @Test
    public void test() {
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

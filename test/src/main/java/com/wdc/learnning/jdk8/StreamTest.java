package com.wdc.learnning.jdk8;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by wangdachong on 2017/7/26.
 */
public class StreamTest {
    @Test
    public void test1() {
        List<Data> list = Arrays.asList(
                new Data(1,"wdc"),
                new Data(2,"wqh"),
                new Data(3,"wbl"),
                new Data(4,"wn"),
                new Data(5,"lbr")
        );
        List<Data> filterList = Arrays.asList(
                new Data(1,"wdc"),
                new Data(4,"wn"),
                new Data(5,"lbr")
        );

        System.out.println(list.stream().filter(data -> {
            long count = filterList.stream().filter(data1 -> data.id.equals(data1.id)).count();
            return count <= 0;
        }).collect(Collectors.toList()));
    }

    private static class Data {
        private Integer id;
        private String name;

        public Data() {
        }

        public Data(Integer id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
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
    }
}

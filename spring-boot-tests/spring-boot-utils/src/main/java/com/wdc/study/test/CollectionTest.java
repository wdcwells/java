package com.wdc.study.test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wdc
 * @date 2018/7/31
 */
public class CollectionTest {
    public static void main(String[] args) {
        toArray();
    }

    private static void toArray() {
        class Data {
            int id;

            public Data(int id) {
                this.id = id;
            }
        }
        List<Data> source = new ArrayList<>();
        source.add(new Data(1));
        source.add(new Data(2));

        List<Data> target = new ArrayList<>(source);//底层用toArray
        target.get(0).id = 10;
        System.out.println(source.get(0).id);
        System.out.println(source.toArray() == target.toArray());
        System.out.println(source.toArray()[0] == target.toArray()[0]);

    }
}

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
        clear();
    }

    private static void toArray() {
        List<Data> source = newList();
        List<Data> target = new ArrayList<>(source);//底层用toArray
        target.get(0).id = 10;
        System.out.println(source.toArray() == target.toArray());
        System.out.println(source.toArray()[0] == target.toArray()[0]);
    }

    private static void clear() {
        List<Data> source = newList();
        System.out.println(source.size());
        List<Data> subList = source.subList(source.size() / 2, source.size());//just a view
        System.out.println(subList.size());
        subList.clear();//also remove elements of the backing list source
        System.out.println(source.size());
    }


    private static List<Data> newList() {
        ArrayList<Data> list = new ArrayList<>();
        list.add(new Data(1));
        list.add(new Data(2));
        return list;
    }


    static class Data {
        int id;

        public Data(int id) {
            this.id = id;
        }
    }
}

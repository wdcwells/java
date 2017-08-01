package com.wdc.learnning.compare;

import org.junit.Test;

/**
 * Created by wangdachong on 2017/7/31.
 */
public class CompareTest {
    @Test
    public void testIntegerEqual() {
        System.out.println(new Data(1, "wdc").getId().equals(
                new Data(1, "wqh").getId()
        ));
    }

    private class Data{
        private Integer id;
        private String name;

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
    }
}

package com.wdc.learnning.compare;

import org.junit.Test;

import java.math.BigDecimal;

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

    @Test
    public void testInteger() {
        Integer id = Integer.valueOf(1);
        Integer id1 = Integer.valueOf(1);
        Integer id2 = new Integer(1);
        int id3 = 1;
        System.out.println(id.equals(id1));
        System.out.println(id == id1);

        System.out.println(id.equals(id2));
        System.out.println(id == id2);

        System.out.println(id.equals(id3));
        System.out.println(id == id3);
    }

    @Test
    public void testString() {
        String s1 = "wdc";
        String s2 = String.valueOf("wdc");
        String s3 = new String("wdc");
        String s4 = "w" + "d" + "c";

        System.out.println(s1.equals(s2));
        System.out.println(s1 == s2);

        System.out.println(s1.equals(s3));
        System.out.println(s1 == s3);

        System.out.println(s1.equals(s4));
        System.out.println(s1 == s4);
    }

    @Test
    public void testBigDecimal() {
        System.out.println(0.06 + 0.01);
        System.out.println(1.0 - 0.42);
        System.out.println(4.015 * 100);
        System.out.println(303.1 / 1000);

        System.out.println(BigDecimal.valueOf(0.06).add(BigDecimal.valueOf(0.01)).doubleValue());
        System.out.println(BigDecimal.valueOf(0.06).add(BigDecimal.valueOf(0.01)).floatValue());
        System.out.println(BigDecimal.valueOf(1.0).subtract(BigDecimal.valueOf(0.42)).doubleValue());
        System.out.println(BigDecimal.valueOf(1.0).subtract(BigDecimal.valueOf(0.42)).floatValue());
        System.out.println(BigDecimal.valueOf(4.015).multiply(BigDecimal.valueOf(100)).doubleValue());
        System.out.println(BigDecimal.valueOf(4.015).multiply(BigDecimal.valueOf(100)).floatValue());
        System.out.println(BigDecimal.valueOf(303.1).divide(BigDecimal.valueOf(1000)).doubleValue());
        System.out.println(BigDecimal.valueOf(303.1).divide(BigDecimal.valueOf(1000)).floatValue());
    }

    private class Data {
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

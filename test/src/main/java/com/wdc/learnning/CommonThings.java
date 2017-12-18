package com.wdc.learnning;

public class CommonThings {
    public static void main(String[] args) {
        testSameHashCodeEqual();
    }

    /**
     * 说明：对象内存地址与hashcode是两个东西
     */
    private static void testSameHashCodeEqual() {
        TestHash testHash1 = new TestHash(1);
        TestHash testHash2 = new TestHash(2);
        System.out.println(testHash1.equals(testHash2));
    }

    private static class TestHash {
        private static final transient int hashcode = 1;

        private Integer id;

        public TestHash(Integer id) {
            this.id = id;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        @Override
        public int hashCode() {
            return hashcode;
        }
    }
}

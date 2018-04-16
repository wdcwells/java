package com.wdc.learnning.methods;

public class MethodsTest {
    public static void main(String[] args) {
        //* final 修饰符 没有传递给下一个方法
        final FinalObj wdc = new FinalObj("wdc");
        FinalObj wqh = changeObj(wdc);
        System.out.println(wqh.getName());
    }

    private static FinalObj changeObj(FinalObj wdc) {
//        wdc.setName("wqh");
        wdc = new FinalObj(wdc.getName() + "1");
        return wdc;
    }


    private static class FinalObj {
        private String name;

        public FinalObj(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}

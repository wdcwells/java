package com.wdc.learnning.encapsulation;

public class TestAnonymousInnerClass {
    private static final String class_final_var = "class_final_var";
    private static String class_var = "class_var";
    private final String instance_final_var = "instance_final_var";
    private String instance_var = "instance var";
    public static void main(String[] args) {
        String local_var = "local var";

        System.out.println(new Hello() {
            @Override
            public String hello() {
                return class_final_var;
            }
        }.hello());

        System.out.println(((Hello) () -> class_var).hello());

        System.out.println(((Hello) () -> local_var).hello());

        System.out.println(((Hello) () -> new TestAnonymousInnerClass().instance_final_var).hello());

        System.out.println(((Hello) () -> new TestAnonymousInnerClass().instance_var).hello());
    }
}

interface Hello {
    String hello();
}

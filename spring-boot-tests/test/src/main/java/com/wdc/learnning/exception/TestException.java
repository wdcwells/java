package com.wdc.learnning.exception;

public class TestException {

    public static void main(String[] args) {
        try {
            test();
        } catch (MyException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    static void test() throws MyException {
        throw new MyException("MyException");
    }

}

class MyException extends Exception {
    public MyException() {
    }

    public MyException(String message) {
        super(message);
    }
}

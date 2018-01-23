package com.wdc.learnning.exception;

public class TryCatchTest {
    public static void main(String[] args) throws Exception{
        testTryCatch();
    }

    private static void testTryCatch() throws Exception{
        try {
            throw new MyException();
        } catch (MyException e) {
            throw new MyException("new");
        } catch (Exception e) {
//            e.printStackTrace();
        }
    }
}

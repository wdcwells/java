package com.wdc.learnning;

import java.io.*;

public class TestSerial {

    public static void main(String[] args) throws Exception {
        B b = new B(1, "1");
        saveObjectToFile("test", b);

        Object obj = getObjectFromFile("test");

        try {
            A a = (A)obj;
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            B nb = (B)obj;
            System.out.println(nb.getF1() + ":" + nb.getF2());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    static void saveObjectToFile(String file, Object obj) throws Exception{
        FileOutputStream out = new FileOutputStream(new File(file));
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(out);
        objectOutputStream.writeObject(obj);

        objectOutputStream.close();
        out.close();
    }

    static Object getObjectFromFile(String file) throws Exception{
        FileInputStream in = new FileInputStream(new File(file));
        ObjectInputStream objectInputStream = new ObjectInputStream(in);
        Object obj = objectInputStream.readObject();

        objectInputStream.close();
        in.close();
        return obj;
    }

}

class A {
    private Integer f1;
    private String f2;
    private Integer f3;

    public A() {
    }

    public A(Integer f1, String f2, Integer f3) {
        this.f1 = f1;
        this.f2 = f2;
        this.f3 = f3;
    }
}

class B implements Serializable{
    private Integer f1;
    private String f2;

    public Integer getF1() {
        return f1;
    }

    public void setF1(Integer f1) {
        this.f1 = f1;
    }

    public String getF2() {
        return f2;
    }

    public void setF2(String f2) {
        this.f2 = f2;
    }

    public B() {
    }

    public B(Integer f1, String f2) {
        this.f1 = f1;
        this.f2 = f2;
    }
}

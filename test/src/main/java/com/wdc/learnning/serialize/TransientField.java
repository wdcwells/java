package com.wdc.learnning.serialize;

import java.io.*;

public class TransientField implements Serializable {

    private static final long serialVersionUID = -361716854884547218L;
    /**
     * 此变量不参与序列化
     */
    private transient String name;

    private Integer age;

    private String newFiled = "123";

    public TransientField(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public static void main(String[] args) throws Exception {
//        testTransient();
        testClassChanged();
    }

    private static void testClassChanged() throws Exception{
        String file = "tmp.txt";
        writeToFile(file, new TransientField("abc", 10));
        TransientField obj = readFromFile(file);
        System.out.println(obj.getName() + ":" + obj.getAge() + ":" + obj.newFiled);
    }

    private static void testTransient() throws Exception {
        TransientField before = new TransientField("wdc", 1);
        ByteArrayOutputStream bytesOut = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(bytesOut);
        out.writeObject(before);

        byte[] bytes = bytesOut.toByteArray();
        ByteArrayInputStream bytesIn = new ByteArrayInputStream(bytes);
        ObjectInputStream in = new ObjectInputStream(bytesIn);
        TransientField after = (TransientField) in.readObject();

        if (null != in)
            in.close();
        if (null != bytesIn)
            bytesIn.close();
        if (null != out)
            out.close();
        if (null != bytesOut)
            bytesOut.close();

        System.out.println(before.getName() + ":" + before.getAge());
        System.out.println(after.getName() + ":" + after.getAge());
    }

    private static void writeToFile(String file, TransientField obj) throws Exception {
        try (
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)
        ){
            objectOutputStream.writeObject(obj);
        }
    }

    private static TransientField readFromFile(String file) throws Exception {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file))){
            return (TransientField) objectInputStream.readObject();

        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}

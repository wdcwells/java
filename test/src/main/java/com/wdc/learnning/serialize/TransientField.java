package com.wdc.learnning.serialize;

import java.io.*;

public class TransientField implements Serializable{
    /**
     * 此变量不参与序列化
     */
    private transient String name;

    private Integer age;

    public TransientField(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
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

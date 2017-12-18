package com.wdc.learnning.bytes;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class BytesTest {

    public static void main(String[] args) throws IOException {
//        byte[] bytes = fileToHex("/Users/wdc/Develop/WorkSpace/IdeaProjects/GitHub/learning/test/src/main/java/com/wdc/learnning/bytes/BytesTest.class");
//        byte[] bytes = staticBytes();
//        byte[] bytes = byteTest();
        System.out.println(10 + ":" + Integer.toBinaryString(10));
        System.out.println(-10 + ":" + Integer.toBinaryString(-10));
        System.out.println(0xfffffff6 + ":" + Integer.toBinaryString(0xfffffff6));
        System.out.println(-889275714 + ":" + Integer.toHexString(-889275714));
//        System.out.println(bytes.length);
//        for (int i = 0; i < bytes.length; i++) {
//            int aByte = /*0xff & */bytes[i];
//            System.out.println(aByte + ":" + Integer.toBinaryString(aByte));
//        }
    }

    public static byte[] fileToHex(String file) throws IOException {
        try (ByteArrayOutputStream bytesOut = new ByteArrayOutputStream(1024)) {
            Files.copy(Paths.get(file), bytesOut);
            return bytesOut.toByteArray();
        }
    }

    public static byte[] staticBytes() {
        byte[] bytes = new byte[10];
        for (int i = 0; i < 9; i++) {
            bytes[i] = (byte) i;
        }
        bytes[9] = (byte) 263;
        return bytes;
    }

    public static byte[] byteTest() {
        byte[] bytes = new byte[1];
        bytes[0] = (byte) (69 + 87);
        return bytes;
    }
}

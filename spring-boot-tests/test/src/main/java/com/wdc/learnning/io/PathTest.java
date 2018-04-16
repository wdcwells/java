package com.wdc.learnning.io;

import java.nio.file.Paths;

public class PathTest {
    public static void main(String[] args) {
        System.out.println("当前目录：" + curDir());
    }

    private static String curDir() {
        return Paths.get(PathTest.class.getResource("").getPath()).toAbsolutePath().toString();
    }
}

package com.wdc.study.libs;

import net.glxn.qrgen.javase.QRCode;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author wdc
 * @date 2018/10/22
 */
public class QrcodeTest {
    public static void main(String[] args) throws IOException {
        // get QR file from text using defaults
        File file = QRCode.from("http://www.baidu.com").file();
        Files.copy(file.toPath(), Paths.get("/Users/wdc/Desktop", file.getName()));
        File bigFile = QRCode.from("http://www.baidu.com").withSize(350, 350).file();
        Files.copy(bigFile.toPath(), Paths.get("/Users/wdc/Desktop", bigFile.getName()));
    }
}

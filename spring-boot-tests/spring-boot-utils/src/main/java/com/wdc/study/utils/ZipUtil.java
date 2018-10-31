package com.wdc.study.utils;

import java.io.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class ZipUtil {
    private static final int BUFFER_SIZE = 1024;

    /**
     * 压缩
     */
    public static byte[] compress(String str) throws IOException {
        if (str == null || str.length() == 0) {
            return new byte[0];
        }
        try(ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            try (GZIPOutputStream gzip = new GZIPOutputStream(out, BUFFER_SIZE)) {
                gzip.write(str.getBytes());
            }
            return out.toByteArray();
        }
    }

    /**
     * 解压缩
     */
    public static String uncompress(byte[] b) throws IOException {
        try(ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            try (GZIPInputStream gzip = new GZIPInputStream(new ByteArrayInputStream(b), BUFFER_SIZE)) {
                byte[] buffer = new byte[BUFFER_SIZE];
                int n;
                while ((n = gzip.read(buffer)) >= 0) {
                    out.write(buffer, 0, n);
                }
            }
            return out.toString();
        }
    }


    public static void main(String[] args) throws Exception {
        StringBuilder builder = new StringBuilder();
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(ZipUtil.class.getResource("ZipUtil.class").openStream()));) {
            String tmp;
            while ((tmp = (reader.readLine())) != null) {
                builder.append(tmp);
            }
        }
        String collect = builder.toString();
        System.out.println("压缩前大小:" + collect.getBytes().length);
        byte[] compress = compress(collect);
        System.out.println("压缩后大小:" + compress.length);
        String uncompress = uncompress(compress);
        System.out.println("解压后大小:" + uncompress.getBytes().length);
        System.out.println(uncompress);
    }

}
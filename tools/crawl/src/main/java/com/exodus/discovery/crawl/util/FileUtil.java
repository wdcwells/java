package com.exodus.discovery.crawl.util;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Properties;
import java.util.UUID;

import static org.junit.Assert.assertTrue;

/**
 * Created by wangdachong on 2017/3/15.
 */
public class FileUtil {
    /**
     * 把流持久化到临时文件夹，备用
     * @param inputStream 输入流
     * @param fileName 文件名
     * @return File
     * @throws Exception
     */
    public static File writeStreamToTmpFile(InputStream inputStream, String fileName) throws Exception {
        assertTrue("inputstream is null", null != inputStream);
        if (null == fileName) fileName = UUID.randomUUID().toString();
        File tmp = new File(System.getProperty("java.io.tmpdir"), fileName);
        if (tmp.exists()) return tmp;
        FileOutputStream outputStream = new FileOutputStream(tmp);
        byte[] bytes = new byte[1024];
        int len;
        while ((len = inputStream.read(bytes)) > -1) {
            outputStream.write(bytes, 0, len);
        }
        outputStream.flush();
        outputStream.close();
        return tmp;
    }

    /**
     * 加载properties文件
     * @param path 路径
     * @param isAbsolute true: classpath下相对路径，false: 取绝对路径
     * @return
     * @throws Exception
     */
    public static Properties loadProperties(String path, boolean isAbsolute) throws Exception{
        assertTrue("文件路径不能为空", (null != path && path.length() > 0));
        Properties properties = new Properties();
        InputStream inputStream = null;
        if (isAbsolute) {
            inputStream = new FileInputStream(path);
        } else {
            inputStream = FileUtil.class.getResourceAsStream(path);
        }
        properties.load(inputStream);
        inputStream.close();
        return properties;
    }

    public static void main(String[] args) throws Exception {
    }
}

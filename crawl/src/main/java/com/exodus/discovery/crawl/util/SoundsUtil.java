package com.exodus.discovery.crawl.util;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by wangdachong on 2017/3/15.
 */
public class SoundsUtil {

    /**
     * 采集中断警报
     * @throws Exception
     */
    public static void warn() throws Exception {
        File soundFile = FileUtil.writeStreamToTmpFile(
                SoundsUtil.class.getResourceAsStream("/warn.wav"), "warn.wav");
        AudioInputStream audioInputStream = null;
        try {
            audioInputStream = AudioSystem.getAudioInputStream(soundFile);
        } catch (Exception e1) {
            e1.printStackTrace();
            return;
        }
        AudioFormat format = audioInputStream.getFormat();
        SourceDataLine auline = null;
        DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
        try {
            auline = (SourceDataLine) AudioSystem.getLine(info);
            auline.open(format);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        auline.start();
        int nBytesRead = 0;
        byte[] abData = new byte[512];
        try {
            while (nBytesRead != -1) {
                nBytesRead = audioInputStream.read(abData, 0, abData.length);
                if (nBytesRead >= 0)
                    auline.write(abData, 0, nBytesRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        } finally {
            auline.drain();
            auline.close();
        }
    }
}

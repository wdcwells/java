package com.wdc.study.utils;

import org.patchca.color.SingleColorFactory;
import org.patchca.filter.predefined.CurvesRippleFilterFactory;
import org.patchca.service.ConfigurableCaptchaService;
import org.patchca.utils.encoder.EncoderHelper;

import java.awt.*;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LibCaptchaUtil {

    public static void main(String[] args) {
        String format = "png";
        String tmpFile = "cap." + format;
        try (OutputStream fos = Files.newOutputStream(Paths.get("/Users/wdc/Desktop", tmpFile))) {
            ConfigurableCaptchaService cs = new ConfigurableCaptchaService();
            cs.setColorFactory(new SingleColorFactory(new Color(25, 60, 170)));
            cs.setFilterFactory(new CurvesRippleFilterFactory(cs.getColorFactory()));
            String challenge = EncoderHelper.getChallangeAndWriteImage(cs, format, fos);
            System.out.println(challenge);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package com.wdc.test.utils;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SignUtil {
    /**
     * 1、参数名按字典排序
     * 2、拼接
     * @param params
     * @return
     */
    public static String sortedSign(Map<String, String> params) {
        List<String> keys = new ArrayList<>(params.keySet());
        Collections.sort(keys);
        return keys.stream().map(key -> {
            StringBuilder tmp = new StringBuilder();
            tmp.append(key).append("=").append(params.get(key));
            return tmp.toString();
        }).collect(Collectors.joining("&"));
    }

    public static byte[] rsaSign(byte[] content, PrivateKey key, String signAlgrithm) {
        try {
            Signature signature = Signature.getInstance(signAlgrithm);
            signature.initSign(key);
            signature.update(content);
            return signature.sign();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content;
    }


    public static boolean rsaVerifySign(byte[] content, byte[] sign, PublicKey key, String signAlgrithm) {
        try {
            Signature signature = Signature.getInstance(signAlgrithm);
            signature.initVerify(key);
            signature.update(content);
            return signature.verify(sign);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
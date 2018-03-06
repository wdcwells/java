package com.wdc.test.utils;


import javax.crypto.Cipher;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class CodecUtil {
    public static void main(String[] args) {
        RSAPublicKey publicKey = rsaLoadPub("/Users/wdc/Develop/Tools/Rsa/rsa_public_key.pem");
        RSAPrivateKey privateKey = rsaLoadPri("/Users/wdc/Develop/Tools/Rsa/pkcs8_rsa_private_key.pem");
        byte[] wdc = rsaEncrypt(publicKey, "wdc".getBytes());
        System.out.println(new String(wdc));
        System.out.println(new String(rsaDecrypt(privateKey, wdc)));
    }

    public static byte[] md5(byte[] param) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            return md5.digest(param);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] base64Encode(byte[] param) {
        return Base64.getEncoder().encode(param);
    }

    public static byte[] base64Decode(byte[] param) {
        return Base64.getDecoder().decode(param);
    }

    public static byte[] rsaEncrypt(RSAPublicKey key, byte[] text) {
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            return cipher.doFinal(text);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return text;
    }

    public static byte[] rsaDecrypt(RSAPrivateKey key, byte[] text) {
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, key);
            return cipher.doFinal(text);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return text;
    }

    public static RSAPublicKey rsaLoadPub(String file) {
        String pubKey;
        if (null != (pubKey = readKeyFromPemFile(file))) {
            return rsaDecodePubKey(pubKey);
        }
        return null;
    }

    private static RSAPublicKey rsaDecodePubKey(String pubKey) {
        try {
            byte[] base64Key = base64Decode(pubKey.getBytes());
            KeyFactory rsa = KeyFactory.getInstance("RSA");
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(base64Key);
            return (RSAPublicKey) rsa.generatePublic(keySpec);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static RSAPrivateKey rsaLoadPri(String file) {
        String priKey;
        if (null != (priKey = readKeyFromPemFile(file))) {
            return rsaDecodePKCS8PriKey(priKey);
        }
        return null;
    }

    private static RSAPrivateKey rsaDecodePKCS8PriKey(String priKey) {
        try {
            byte[] base64Key = base64Decode(priKey.getBytes());
            KeyFactory rsa = KeyFactory.getInstance("RSA");
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(base64Key);
            return (RSAPrivateKey) rsa.generatePrivate(keySpec);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String readKeyFromPemFile(String file) {
        File pubFile = new File(file);
        if (pubFile.exists()) {
            try (BufferedReader br = new BufferedReader(new InputStreamReader((new FileInputStream(pubFile))))) {
                String readLine = null;
                StringBuilder sb = new StringBuilder();
                while ((readLine = br.readLine()) != null) {
                    if (readLine.charAt(0) == '-') {
                        continue;
                    } else {
                        sb.append(readLine);
//                        sb.append('\r');//bug 导致base64解码失败
                    }
                }
                return sb.toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }


}

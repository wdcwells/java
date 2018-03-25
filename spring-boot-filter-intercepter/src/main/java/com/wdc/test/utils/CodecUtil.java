package com.wdc.test.utils;


import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Key;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class CodecUtil {
    //    private static final Path DEFAULT_AES_KEY_PATH = Paths.get(CodecUtil.class.getResource("/").getPath(), "aes.key").toAbsolutePath();
    private static final String AES_KEY_ALGORITHM = "AES";
    private static final String RSA_CIPER_ALGRITHM = "RSA";
    private static final String AES_CIPER_ALGRITHM = "AES/ECB/PKCS5Padding";

    public static void main(String[] args) {
        RSAPublicKey publicKey = rsaLoadPubFromFile("/Users/wdc/Develop/Tools/Rsa/rsa_public_key.pem");
        RSAPrivateKey privateKey = rsaLoadPriFromFile("/Users/wdc/Develop/Tools/Rsa/pkcs8_rsa_private_key.pem");
        byte[] wdc = rsaEncrypt(publicKey, "wdc".getBytes());
        byte[] wqh = rsaEncrypt(privateKey, "wqh".getBytes());
        System.out.println(new String(wdc));
        System.out.println(new String(wqh));
        System.out.println(new String(rsaDecrypt(privateKey, wdc)));
        System.out.println(new String(rsaDecrypt(publicKey, wqh)));

        SecretKey secretKey = aesGenKey();
        byte[] aesEncrypt = aesEncrypt("jd".getBytes(), secretKey);
        byte[] aesDecrypt = aesDecrypt(aesEncrypt, secretKey);
        System.out.println(new String(aesDecrypt));


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

    public static byte[] rsaEncrypt(Key key, byte[] text) {
        try {
            Cipher cipher = Cipher.getInstance(RSA_CIPER_ALGRITHM);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            return cipher.doFinal(text);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return text;
    }

    public static byte[] rsaDecrypt(Key key, byte[] text) {
        try {
            Cipher cipher = Cipher.getInstance(RSA_CIPER_ALGRITHM);
            cipher.init(Cipher.DECRYPT_MODE, key);
            return cipher.doFinal(text);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return text;
    }

    public static RSAPublicKey rsaLoadPubFromFile(String file) {
        String pubKey;
        if (null != (pubKey = readKeyFromFile(file))) {
            return rsaDecodePubKey(pubKey);
        }
        return null;
    }

    public static RSAPrivateKey rsaLoadPriFromFile(String file) {
        String priKey;
        if (null != (priKey = readKeyFromFile(file))) {
            return rsaDecodePKCS8PriKey(priKey);
        }
        return null;
    }

    private static byte[] aesCrypt(byte[] content, Key key, int crypt) {
        try {
            Cipher cipher = Cipher.getInstance(AES_CIPER_ALGRITHM);
            cipher.init(crypt, key);
            return cipher.doFinal(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content;
    }

    public static byte[] aesEncrypt(byte[] content, Key key) {
        return aesCrypt(content, key, Cipher.ENCRYPT_MODE);
    }

    public static byte[] aesEncrypt(byte[] content, String path) {
        SecretKey secretKey = aesReadKeyFromFile(path);
        if (null != secretKey) {
            return aesEncrypt(content, secretKey);
        }
        return content;
    }

    public static byte[] aesDecrypt(byte[] content, Key key) {
        return aesCrypt(content, key, Cipher.DECRYPT_MODE);
    }

    public static byte[] aesDecrypt(byte[] content, String path) {
        SecretKey secretKey = aesReadKeyFromFile(path);
        if (null != secretKey) {
            return aesDecrypt(content, secretKey);
        }
        return content;
    }

    public static SecretKey aesGenKey() {
        return aesGenKey(null, null);
    }

    public static SecretKey aesGenKey(byte[] password) {
        return aesGenKey(password, null);
    }

    private static SecretKey aesGenKey(byte[] seed, String path) {
        try {
            KeyGenerator aes = KeyGenerator.getInstance(AES_KEY_ALGORITHM);
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");//指明后 每次生成密码一样
            if (null != seed && seed.length > 0) {
                random.setSeed(seed);
            }
            aes.init(random);
            SecretKey secretKey = aes.generateKey();
            if (null != path && path.trim().length() > 0) {
                aesStoreKeyToFile(secretKey, path);
            }
            return secretKey;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void aesStoreKeyToFile(Key secretKey, String path) {
        try {
            Path store = Paths.get(path);
            Files.write(store, secretKey.getEncoded());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static SecretKey aesReadKeyFromFile(String path) {
        SecretKeySpec secretKeySpec = null;
        if (null != path && path.trim().length() > 0) {
            try {
                byte[] bytes = Files.readAllBytes(Paths.get(path));
                secretKeySpec = new SecretKeySpec(bytes, AES_KEY_ALGORITHM);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return secretKeySpec;
    }

    private static RSAPublicKey rsaDecodePubKey(String pubKey) {
        try {
            byte[] base64Key = base64Decode(pubKey.getBytes());
            KeyFactory rsa = KeyFactory.getInstance(RSA_CIPER_ALGRITHM);
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(base64Key);
            return (RSAPublicKey) rsa.generatePublic(keySpec);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static RSAPrivateKey rsaDecodePKCS8PriKey(String priKey) {
        try {
            byte[] base64Key = base64Decode(priKey.getBytes());
            KeyFactory rsa = KeyFactory.getInstance(RSA_CIPER_ALGRITHM);
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(base64Key);
            return (RSAPrivateKey) rsa.generatePrivate(keySpec);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String readKeyFromFile(String file) {
        boolean classpathFile = false;
        if (file.startsWith("classpath:")) {
            classpathFile = true;
        }
        try (BufferedReader br = new BufferedReader(new InputStreamReader
                (classpathFile ? CodecUtil.class.getResourceAsStream(file.substring(10))
                        : (new FileInputStream(new File(file)))))) {
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
        return null;
    }


}

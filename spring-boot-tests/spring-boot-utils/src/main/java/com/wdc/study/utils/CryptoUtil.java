package com.wdc.study.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.ByteArrayOutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Map;
import java.util.Objects;


/**
 * @author wdc
 * @date 2018/8/14
 */
public class CryptoUtil {
    private static final Logger logger = LoggerFactory.getLogger(CryptoUtil.class);

    private static final String LOCAL_PRIVATE_KEY = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALhvii/RhxmejXIhziIa7k9CpDVVKuqFyMBCQ8Ln73/Sf8JiDqRcKl1Dp7WOZ84YT0jPgazY9R3CJBKb9k+CYk1AtFkn0O3sqn4+zVKZHiW9JYdx00anUmQjI5RyicPICb9ldKsTHXLQbHhkoCqPvLArGhjTPH0lbRa8U2Wo6JyXAgMBAAECgYAf+xdXG1n100xrkfkQaOMR01ht9wquB+y7icH+NgGNhI6qwh4P2ftumKCoFr1hO03kLjcqJaS7OELRB0I6nz00JDd90FXU4m1xcI3qzDRXz6kjmcgt3bcGfg0EwgUE7wlYAteo2v+0TIEAt19rI+jn8dsmoDW4wIrIFo8gV9JUYQJBAPRf42F2xta0T83klMfeHcHUDRNTGzcrQl8SUs+7w6SKkO7A/T7N0/qD66iDJrWk0rPvhmmOTEJJEzBj2H/K0vMCQQDBNa/mKy2obTfylzUS9YK10p76TeWPlLMtgY8mxkZVGHqC9tEurYesP6hjzamT5a13GLgu1NN7tLcDhzrTF5DNAkBwIPtdQjnUVGH10DdwSfuPb3QcdNqdAs1ugCLdTINloWsFEiphQeJNJXDhHmSJnjpL89DR6EgXdh5G6tlxMah/AkA1DRPGZHa993dUQhaNpSIF4NZMLlVpLlBgn2F6KRkoxnblPPZFFdrZ/gSzmsYEWR1fRUdVfJSxM/bInjoVujPFAkEAkkahphDgDcJjXBSDoBEobuPc4UubkBidwI5l0f0pNf2guek5Ws+/1No110/Ybv/uGCADvFepMNACWAR5mihQuA==";
    private static final String LOCAL_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC4b4ov0YcZno1yIc4iGu5PQqQ1VSrqhcjAQkPC5+9/0n/CYg6kXCpdQ6e1jmfOGE9Iz4Gs2PUdwiQSm/ZPgmJNQLRZJ9Dt7Kp+Ps1SmR4lvSWHcdNGp1JkIyOUconDyAm/ZXSrEx1y0Gx4ZKAqj7ywKxoY0zx9JW0WvFNlqOiclwIDAQAB";
    private static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;
    private static final Base64.Encoder base64Encoder = Base64.getEncoder();
    private static final Base64.Decoder base64Decoder = Base64.getDecoder();
    private static final int INIT_KEY_SIZE_DEFAULT = 128;
    private static final int RSA_MAX_ENCRYPT_BLOCK_SIZE = 117;
    private static final int RSA_MAX_DECRYPT_BLOCK_SIZE = 128;

    static {
//        fixKeyLength();
    }

    public static void main(String[] args) throws Exception {
        //region aes test
        System.out.println(base64Encoder.encodeToString(aesSimpleKey("1234567812345678").getEncoded()));
        System.out.println(base64Encoder.encodeToString(genKey(KeyGeneratorEnum.AES.name()).getEncoded()));
        System.out.println(base64Encoder.encodeToString(genKey(KeyGeneratorEnum.AES.name(), SecureRandomEnum.NativePRNGBlocking.name()).getEncoded()));
        System.out.println(base64Encoder.encodeToString(genKeyWithSeed(KeyGeneratorEnum.AES.name(), "1234567812345678").getEncoded()));

        SecretKey aesKey = genKey(KeyGeneratorEnum.AES.name());
        String aesEncrypt = aesEncrypt("123", aesKey);
        System.out.println("aesEncrypt:" + aesEncrypt);
        String aesDecrypt = aesDecrypt(aesEncrypt, aesKey);
        System.out.println("aesDecrypt:" + aesDecrypt);
        //endregion

        //region other key gen test
        System.out.println(base64Encoder.encodeToString(genKey(KeyGeneratorEnum.DES.name()).getEncoded()));
        System.out.println(base64Encoder.encodeToString(genKeyWithSeed(KeyGeneratorEnum.DES.name(), "123").getEncoded()));
        //endregion

        System.out.println(Cipher.getMaxAllowedKeyLength("AES"));
        System.out.println(Cipher.getMaxAllowedKeyLength("RSA"));

        StringBuilder content = new StringBuilder();
        for (int i = 0; i < 128; i++) {
            content.append("1");
        }
        //region rsa
        System.out.println(rsaDecryptByPri(rsaEncryptByPub("123", LOCAL_PUBLIC_KEY), LOCAL_PRIVATE_KEY));
        System.out.println(rsaDecryptByPri(rsaEncryptByPub(content.toString(), LOCAL_PUBLIC_KEY), LOCAL_PRIVATE_KEY));
        System.out.println(rsaDecryptByPub(rsaEncryptByPri(content.toString(), LOCAL_PRIVATE_KEY), LOCAL_PUBLIC_KEY));
        //endregion

        //region rsa sign
        System.out.println(rsaVerifySign("123", rsaSign("123", LOCAL_PRIVATE_KEY, SignatureEnum.MD5withRSA)
                , LOCAL_PUBLIC_KEY, SignatureEnum.MD5withRSA));
        System.out.println(rsaVerifySign(content.toString(), rsaSign(content.toString(), LOCAL_PRIVATE_KEY, SignatureEnum.MD5withRSA)
                , LOCAL_PUBLIC_KEY, SignatureEnum.MD5withRSA));
        //endregion

        //region digest
        System.out.println(messageDigest("1234", MessageDigestEnum.MD5));
        //endregion

    }

    /**
     * 摘要
     *
     * @param data
     * @param alg
     * @return
     */
    public static String messageDigest(String data, MessageDigestEnum alg) {
        try {
            MessageDigest digest = MessageDigest.getInstance(alg.name());
            digest.update(data.getBytes(DEFAULT_CHARSET));
            return toHexString(digest.digest());
        } catch (Exception e) {
            logger.error("error in messageDigest whith data({}), alg({})", data, alg.name(), e);
        }
        return null;
    }

    /**
     * AES 加密
     *
     * @param data
     * @param secretKey
     */
    public static String aesEncrypt(String data, Key secretKey) {
        try {
            Cipher cipher = Cipher.getInstance(CiperEnum.AES.name());
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encryptedData = cipher.doFinal(data.getBytes(DEFAULT_CHARSET));
            return base64Encoder.encodeToString(encryptedData);
        } catch (Exception e) {
            String errorMsg = String.format("error when aesEncrypt with data(%s), keyAlg(%s)", data, secretKey.getAlgorithm());
            logger.error(errorMsg, e);
            throw new RuntimeException(errorMsg);
        }
    }

    /**
     * AES 解密
     *
     * @param data
     * @param secretKey
     */
    public static String aesDecrypt(String data, Key secretKey) {
        try {
            Cipher cipher = Cipher.getInstance(CiperEnum.AES.name());
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] decodedBytes = base64Decoder.decode(data);
            byte[] original = cipher.doFinal(decodedBytes);
            return new String(original, DEFAULT_CHARSET);
        } catch (Exception e) {
            String errorMsg = String.format("error when aesDecrypt with data(%s), keyAlg(%s)", data, secretKey.getAlgorithm());
            logger.error(errorMsg, e);
            throw new RuntimeException(errorMsg);
        }
    }

    /**
     * 公钥加密
     *
     * @param data   will be split into blocks of size 117 if its byte size is more than 117
     * @param pubKey
     * @return
     */
    public static String rsaEncryptByPub(String data, String pubKey) {
        try {
            byte[] content = data.getBytes(DEFAULT_CHARSET);
            Cipher cipher = Cipher.getInstance(CiperEnum.RSA.name());
            cipher.init(Cipher.ENCRYPT_MODE, rsaPublicKey(pubKey));
            return base64Encoder.encodeToString(doFinal(cipher, content, RSA_MAX_ENCRYPT_BLOCK_SIZE));
        } catch (Exception e) {
            logger.error("error in rsaEncryptByPub with data({}), pubKey({})", data, pubKey, e);
        }
        return null;
    }

    /**
     * 私钥加密
     *
     * @param data   will be split into blocks of size 117 if its byte size is more than 117
     * @param priKey
     * @return
     */
    public static String rsaEncryptByPri(String data, String priKey) {
        try {
            byte[] content = data.getBytes(DEFAULT_CHARSET);
            Cipher cipher = Cipher.getInstance(CiperEnum.RSA.name());
            cipher.init(Cipher.ENCRYPT_MODE, rsaPrivateKey(priKey));
            return base64Encoder.encodeToString(doFinal(cipher, content, RSA_MAX_ENCRYPT_BLOCK_SIZE));
        } catch (Exception e) {
            logger.error("error in rsaEncryptByPri with data({}), priKey({})", data, priKey, e);
        }
        return null;
    }

    /**
     * 私钥解密
     *
     * @param data   will be split into blocks of size 128 if its byte size is more than 128
     * @param priKey
     * @return
     */
    public static String rsaDecryptByPri(String data, String priKey) {
        try {
            byte[] content = base64Decoder.decode(data);
            Cipher cipher = Cipher.getInstance(CiperEnum.RSA.name());
            cipher.init(Cipher.DECRYPT_MODE, rsaPrivateKey(priKey));
            return new String(doFinal(cipher, content, RSA_MAX_DECRYPT_BLOCK_SIZE), DEFAULT_CHARSET);
        } catch (Exception e) {
            logger.error("error in rsaDecryptByPri with data({}), priKey({})", data, priKey, e);
        }
        return null;
    }

    /**
     * 公钥解密
     *
     * @param data   will be split into blocks of size 128 if its byte size is more than 128
     * @param pubKey
     * @return
     */
    public static String rsaDecryptByPub(String data, String pubKey) {
        try {
            byte[] content = base64Decoder.decode(data);
            Cipher cipher = Cipher.getInstance(CiperEnum.RSA.name());
            cipher.init(Cipher.DECRYPT_MODE, rsaPublicKey(pubKey));
            return new String(doFinal(cipher, content, RSA_MAX_DECRYPT_BLOCK_SIZE), DEFAULT_CHARSET);
        } catch (Exception e) {
            logger.error("error in rsaDecryptByPub with data({}), pubKey({})", data, pubKey, e);
        }
        return null;
    }

    /**
     * 私钥签名
     *
     * @param data
     * @param priKey
     * @param alg
     * @return
     */
    public static String rsaSign(String data, String priKey, SignatureEnum alg) {
        try {
            Signature signature = Signature.getInstance(alg.name());
            signature.initSign(rsaPrivateKey(priKey));
            signature.update(data.getBytes(DEFAULT_CHARSET));
            return base64Encoder.encodeToString(signature.sign());
        } catch (Exception e) {
            logger.error("error in rsaSign with data({}), priKey({}), alg({})", data, priKey, alg.name(), e);
        }
        return null;
    }

    /**
     * 公钥验签
     *
     * @param data
     * @param sign
     * @param pubKey
     * @param alg
     * @return
     */
    public static boolean rsaVerifySign(String data, String sign, String pubKey, SignatureEnum alg) {
        try {
            Signature signature = Signature.getInstance(alg.name());
            signature.initVerify(rsaPublicKey(pubKey));
            signature.update(data.getBytes(DEFAULT_CHARSET));
            return signature.verify(base64Decoder.decode(sign));
        } catch (Exception e) {
            logger.error("error in rsaVerifySign with data({}), sign({}), pubKey({}), alg({})", data, sign, pubKey, alg.name(), e);
        }
        return false;
    }

    /**
     * 获取 SecretKey(AES)
     *
     * @param secret
     * @return
     */
    public static SecretKey aesSimpleKey(String secret) {
        try {
            return new SecretKeySpec(secret.getBytes(DEFAULT_CHARSET), KeyGeneratorEnum.AES.name());
        } catch (Exception e) {
            String errorMsg = String.format("error when aesSimpleKey with secret(%s)", secret);
            logger.error(errorMsg, e);
            throw new RuntimeException(errorMsg);
        }
    }

    /**
     * 获取 SecretKey(通用)
     *
     * @param keyAlg
     * @return
     */
    public static SecretKey genKey(String keyAlg) {
        return genKey(keyAlg, null);
    }

    public static SecretKey genKeyWithSeed(String keyAlg, String seed) {
        return genKey(keyAlg, null, seed);
    }

    public static SecretKey genKey(String keyAlg, String secureAlg) {
        return genKey(keyAlg, secureAlg, null);
    }

    private static byte[] doFinal(Cipher cipher, byte[] data, int max) throws Exception {
        int total = data.length, offset = 0, len;
        byte[] result;
        if (total > max) {
            try (ByteArrayOutputStream bytesOut = new ByteArrayOutputStream()) {
                while (offset < total) {
                    if ((len = total - offset) > max)
                        len = max;
                    byte[] tmp = cipher.doFinal(data, offset, len);
                    bytesOut.write(tmp);
                    offset += len;
                }
                result = bytesOut.toByteArray();
            }
        } else {
            result = cipher.doFinal(data);
        }
        return result;
    }

    private static RSAPublicKey rsaPublicKey(String pubKey) {
        try {
            return (RSAPublicKey) KeyFactory.getInstance(KeyFactoryEnum.RSA.name())
                    .generatePublic(new X509EncodedKeySpec(base64Decoder.decode(pubKey)));
        } catch (Exception e) {
            logger.error("error in rsaPublicKey with pubKey({})", pubKey, e);
        }
        return null;
    }

    private static RSAPrivateKey rsaPrivateKey(String priKey) {
        try {
            return (RSAPrivateKey) KeyFactory.getInstance(KeyFactoryEnum.RSA.name())
                    .generatePrivate(new PKCS8EncodedKeySpec(base64Decoder.decode(priKey)));
        } catch (Exception e) {
            logger.error("error in rsaPrivateKey with priKey({})", priKey, e);
        }
        return null;
    }

    private static SecretKey genKey(String keyAlg, String secureAlg, String seed) {
        int initKeySize = defaultInitKeySize(keyAlg);
        try {
            KeyGenerator generator = KeyGenerator.getInstance(keyAlg);
            SecureRandom secureRandom;
            if (Objects.nonNull(seed) && seed.trim().length() > 0) {
                secureRandom = SecureRandom.getInstance(SecureRandomEnum.SHA1PRNG.name());
                secureRandom.setSeed(seed.getBytes(DEFAULT_CHARSET));
            } else {
                if (Objects.nonNull(secureAlg) && secureAlg.trim().length() > 0) {
                    secureRandom = SecureRandom.getInstance(secureAlg);
                } else {
                    secureRandom = SecureRandom.getInstanceStrong();
                }
            }
            generator.init(initKeySize, secureRandom);
            return generator.generateKey();
        } catch (Exception e) {
            String errorMsg = String.format("error when genKey with keyAlg(%s), secureAlg(%s), seed(%s)", keyAlg, secureAlg, seed);
            logger.error(errorMsg, e);
            throw new RuntimeException(errorMsg);
        }
    }

    private static int defaultInitKeySize(String keyAlg) {
        switch (keyAlg) {
            case "AES":
                return 128;//128, 192 or 256
            case "DES":
                return 56;
            default:
                return INIT_KEY_SIZE_DEFAULT;
        }
    }

    private static String toHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder();
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    private static void fixKeyLength() {
        String errorString = "Failed manually overriding key-length permissions.";
        int newMaxKeyLength;
        try {
            if ((newMaxKeyLength = Cipher.getMaxAllowedKeyLength("AES")) < 256) {
                Class<?> c = Class.forName("javax.crypto.CryptoAllPermissionCollection");
                Constructor<?> con = c.getDeclaredConstructor();
                con.setAccessible(true);
                Object allPermissionCollection = con.newInstance();
                Field f = c.getDeclaredField("all_allowed");
                f.setAccessible(true);
                f.setBoolean(allPermissionCollection, true);

                c = Class.forName("javax.crypto.CryptoPermissions");
                con = c.getDeclaredConstructor();
                con.setAccessible(true);
                Object allPermissions = con.newInstance();
                f = c.getDeclaredField("perms");
                f.setAccessible(true);
                ((Map<String, Object>) f.get(allPermissions)).put("*", allPermissionCollection);

                c = Class.forName("javax.crypto.JceSecurityManager");
                f = c.getDeclaredField("defaultPolicy");
                f.setAccessible(true);
                Field mf = Field.class.getDeclaredField("modifiers");
                mf.setAccessible(true);
                mf.setInt(f, f.getModifiers() & ~Modifier.FINAL);
                f.set(null, allPermissions);

                newMaxKeyLength = Cipher.getMaxAllowedKeyLength("AES");
            }
        } catch (Exception e) {
            throw new RuntimeException(errorString, e);
        }
        if (newMaxKeyLength < 256)
            throw new RuntimeException(errorString); // hack failed
    }

    public enum KeyGeneratorEnum {
        AES("Constructs secret keys for use with the AES algorithm."),
        DES("Constructs secrets keys for use with the DES algorithm."),
        DESede("Constructs secrets keys for use with the DESede (Triple-DES) algorithm."),;
        public String desc;

        KeyGeneratorEnum(String desc) {
            this.desc = desc;
        }
    }

    public enum KeyFactoryEnum {
        RSA("Constructs pub/pri keys for use with the RSA algorithm."),;
        public String desc;

        KeyFactoryEnum(String desc) {
            this.desc = desc;
        }
    }

    public enum SecureRandomEnum {
        SHA1PRNG("The name of the pseudo-random number generation (PRNG) algorithm supplied by the SUN provider. This algorithm uses SHA-1 as the foundation of the PRNG. It computes the SHA-1 hash over a true-random seed value concatenated with a 64-bit counter which is incremented by 1 for each operation. From the 160-bit SHA-1 output, only 64 bits are used."),
        NativePRNGBlocking("Obtains random numbers from the underlying native OS, blocking if necessary. For example, /dev/random on UNIX-like systems."),;
        public String desc;

        SecureRandomEnum(String desc) {
            this.desc = desc;
        }
    }

    public enum CiperEnum {
        AES("Advanced Encryption Standard as specified by NIST in FIPS 197. Also known as the Rijndael algorithm by Joan Daemen and Vincent Rijmen, AES is a 128-bit block cipher supporting keys of 128, 192, and 256 bits."),
        RSA("The RSA encryption algorithm as defined in PKCS #1（http://www.rfc-editor.org/rfc/rfc2437.txt）");
        public String desc;

        CiperEnum(String desc) {
            this.desc = desc;
        }
    }

    public enum SignatureEnum {
        NONEwithRSA("The RSA signature algorithm, which does not use a digesting algorithm (for example, MD5/SHA1) before performing the RSA operation. For more information about the RSA Signature algorithms, see (http://www.rfc-editor.org/rfc/rfc2437.txt)."),
        MD5withRSA("The MD2/MD5 with RSA Encryption signature algorithm, which uses the MD2/MD5 digest algorithm and RSA to create and verify RSA digital signatures as defined in (http://www.rfc-editor.org/rfc/rfc2437.txt)."),
        SHA1withRSA("The signature algorithm with SHA-* and the RSA encryption algorithm as defined in the OSI Interoperability Workshop, using the padding conventions described in (http://www.rfc-editor.org/rfc/rfc2437.txt)."),;
        public String desc;

        SignatureEnum(String desc) {
            this.desc = desc;
        }
    }

    public enum MessageDigestEnum {
        MD5("The MD5 message digest algorithm as defined in RFC 1321(http://www.ietf.org/rfc/rfc1321.txt)."),;
        public String desc;

        MessageDigestEnum(String desc) {
            this.desc = desc;
        }
    }

}

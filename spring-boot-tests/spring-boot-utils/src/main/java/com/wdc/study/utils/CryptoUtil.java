package com.wdc.study.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Objects;

/**
 * @author wdc
 * @date 2018/8/14
 */
public class CryptoUtil {
    private static final Logger logger = LoggerFactory.getLogger(CryptoUtil.class);
    public static final String LOCAL_PRIVATE_KEY = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALhvii/RhxmejXIhziIa7k9CpDVVKuqFyMBCQ8Ln73/Sf8JiDqRcKl1Dp7WOZ84YT0jPgazY9R3CJBKb9k+CYk1AtFkn0O3sqn4+zVKZHiW9JYdx00anUmQjI5RyicPICb9ldKsTHXLQbHhkoCqPvLArGhjTPH0lbRa8U2Wo6JyXAgMBAAECgYAf+xdXG1n100xrkfkQaOMR01ht9wquB+y7icH+NgGNhI6qwh4P2ftumKCoFr1hO03kLjcqJaS7OELRB0I6nz00JDd90FXU4m1xcI3qzDRXz6kjmcgt3bcGfg0EwgUE7wlYAteo2v+0TIEAt19rI+jn8dsmoDW4wIrIFo8gV9JUYQJBAPRf42F2xta0T83klMfeHcHUDRNTGzcrQl8SUs+7w6SKkO7A/T7N0/qD66iDJrWk0rPvhmmOTEJJEzBj2H/K0vMCQQDBNa/mKy2obTfylzUS9YK10p76TeWPlLMtgY8mxkZVGHqC9tEurYesP6hjzamT5a13GLgu1NN7tLcDhzrTF5DNAkBwIPtdQjnUVGH10DdwSfuPb3QcdNqdAs1ugCLdTINloWsFEiphQeJNJXDhHmSJnjpL89DR6EgXdh5G6tlxMah/AkA1DRPGZHa993dUQhaNpSIF4NZMLlVpLlBgn2F6KRkoxnblPPZFFdrZ/gSzmsYEWR1fRUdVfJSxM/bInjoVujPFAkEAkkahphDgDcJjXBSDoBEobuPc4UubkBidwI5l0f0pNf2guek5Ws+/1No110/Ybv/uGCADvFepMNACWAR5mihQuA==";
    public static final String LOCAL_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC4b4ov0YcZno1yIc4iGu5PQqQ1VSrqhcjAQkPC5+9/0n/CYg6kXCpdQ6e1jmfOGE9Iz4Gs2PUdwiQSm/ZPgmJNQLRZJ9Dt7Kp+Ps1SmR4lvSWHcdNGp1JkIyOUconDyAm/ZXSrEx1y0Gx4ZKAqj7ywKxoY0zx9JW0WvFNlqOiclwIDAQAB";
    public static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;
    private static final Base64.Encoder base64Encoder = Base64.getEncoder();
    private static final Base64.Decoder base64Decoder = Base64.getDecoder();

    public enum KeyGeneratorEnum {
        AES("Constructs secret keys for use with the AES algorithm."),
        DES("Constructs secrets keys for use with the DES algorithm."),
        DESede("Constructs secrets keys for use with the DESede (Triple-DES) algorithm."),
        ;
        public String desc;

        KeyGeneratorEnum(String desc) {
            this.desc = desc;
        }
    }

    public enum SecureRandomEnum {
        SHA1PRNG("The name of the pseudo-random number generation (PRNG) algorithm supplied by the SUN provider. This algorithm uses SHA-1 as the foundation of the PRNG. It computes the SHA-1 hash over a true-random seed value concatenated with a 64-bit counter which is incremented by 1 for each operation. From the 160-bit SHA-1 output, only 64 bits are used."),
        NativePRNGBlocking("Obtains random numbers from the underlying native OS, blocking if necessary. For example, /dev/random on UNIX-like systems."),
        ;
        public String desc;

        SecureRandomEnum(String desc) {
            this.desc = desc;
        }
    }

    //region algs
    public static final String AES_CIPER_ALG = "AES";
    public static final String SECURE_RANDOM_ALG_DEFAULT = SecureRandomEnum.SHA1PRNG.name();
    public static final int INIT_KEY_SIZE_DEFAULT = 128;
    //endregion

    /**
     * AES 加密
     * @param data
     * @param secretKey
     */
    public static String aesEncrypt(String data, Key secretKey) {
        try {
            Cipher cipher = Cipher.getInstance(AES_CIPER_ALG);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encryptedData = cipher.doFinal(data.getBytes());
            return base64Encoder.encodeToString(encryptedData);
        } catch (Exception e) {
            String errorMsg = String.format("error when aesEncrypt with data(%s), keyAlg(%s)", data, secretKey.getAlgorithm());
            logger.error(errorMsg, e);
            throw new RuntimeException(errorMsg);
        }
    }

    /**
     * AES 解密
     * @param data
     * @param secretKey
     */
    public static String aesDecrypt(String data, Key secretKey){
        try {
            Cipher cipher = Cipher.getInstance(AES_CIPER_ALG);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] decodedBytes = base64Decoder.decode(data);
            byte[] original = cipher.doFinal(decodedBytes);
            return new String(original);
        } catch (Exception e) {
            String errorMsg = String.format("error when aesDecrypt with data(%s), keyAlg(%s)", data, secretKey.getAlgorithm());
            logger.error(errorMsg, e);
            throw new RuntimeException(errorMsg);
        }
    }

    /**
     * 获取 SecretKey(AES)
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

    public static SecretKey genKey(String keyAlg, String secureAlg, String seed) {
        int initKeySize = defaultInitKeySize(keyAlg);
        try {
            KeyGenerator generator = KeyGenerator.getInstance(keyAlg);
            SecureRandom secureRandom;
            if (Objects.nonNull(seed) && seed.trim().length() > 0) {
                secureRandom = SecureRandom.getInstance(SECURE_RANDOM_ALG_DEFAULT);
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
        return INIT_KEY_SIZE_DEFAULT;
    }

    public static void main(String[] args) throws Exception {
        System.out.println(aesEncrypt("1", aesSimpleKey("1234567812345678")));
        System.out.println(aesEncrypt("1", genKey(KeyGeneratorEnum.AES.name())));
        System.out.println(aesEncrypt("1", genKey(KeyGeneratorEnum.AES.name(), SecureRandomEnum.NativePRNGBlocking.name())));
        System.out.println(aesEncrypt("1", genKeyWithSeed(KeyGeneratorEnum.AES.name(), "1234567812345678")));
    }
}

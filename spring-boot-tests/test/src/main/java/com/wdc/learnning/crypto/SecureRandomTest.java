package com.wdc.learnning.crypto;

import java.security.SecureRandom;

/**
 * @author wdc
 * @date 2018/3/25
 */
public class SecureRandomTest {

    public static void main(String args[]) throws Exception {
//        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
// SHA1PRNG随机数算法
        SecureRandom rng = SecureRandom.getInstance("SHA1PRNG");
        rng.setSeed(21);
// 生成随机数
        int numberToGenerate = 2;
        byte randNumbers[] = new byte[numberToGenerate];
        rng.nextBytes(randNumbers);
// 打印随机数
        for (int j = 0; j < numberToGenerate; j++) {
            System.out.print(randNumbers[j] + " ");
        }
    }
}

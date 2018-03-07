package com.wdc.test.config;

import com.wdc.test.utils.CodecUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@Configuration
public class RsaConfig {
    @Value("${rsa.private}")
    private String priKeyPath;
    @Value("${rsa.public}")
    private String pubKeyPath;

    public RSAPrivateKey getPriKey() {
        return CodecUtil.rsaLoadPriFromFile(priKeyPath);
    }

    public RSAPublicKey getPubKey() {
        return CodecUtil.rsaLoadPubFromFile(pubKeyPath);
    }
}

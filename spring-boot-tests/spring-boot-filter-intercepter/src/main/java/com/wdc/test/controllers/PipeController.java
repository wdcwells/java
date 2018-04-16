package com.wdc.test.controllers;

import com.wdc.test.config.RsaConfig;
import com.wdc.test.utils.CodecUtil;
import com.wdc.test.utils.JsonUtil;
import com.wdc.test.utils.SignUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;

import javax.crypto.SecretKey;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("pipe")
public class PipeController {
    private final RestTemplate restTemplate;
    private final AsyncRestTemplate asyncRestTemplate;
    private final RsaConfig rsaConfig;

    @Autowired
    public PipeController(RestTemplate restTemplate, AsyncRestTemplate asyncRestTemplate, RsaConfig rsaConfig) {
        this.restTemplate = restTemplate;
        this.asyncRestTemplate = asyncRestTemplate;
        this.rsaConfig = rsaConfig;
    }

    @GetMapping("test/baidu")
    String testRest() {
        return restTemplate.getForObject("https://www.baidu.com", String.class);
    }

    @GetMapping("test/async/baidu")
    ResponseEntity<String> testRestAsync() {
        ListenableFuture<ResponseEntity<String>> future = asyncRestTemplate.getForEntity("https://www.baidu.com", String.class);
        long before = System.currentTimeMillis();
        while (!future.isDone()) {
            System.out.println("处理中………………");
        }
        long after = System.currentTimeMillis();
        System.out.println("耗时毫秒：" + (after - before));
        ResponseEntity<String> responseEntity = null;
        try {
            responseEntity = future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseEntity;
    }

    @PostMapping("test/flow/user/check")
    Object checkUser() {
        String md5WithRSA = "MD5WithRSA";
        byte[] aesKey = "wdc".getBytes();
        SecretKey aesGenKey = CodecUtil.aesGenKey(aesKey);
        HashMap<String, String> params = new HashMap<>();
        HashMap<String, String> bizData = new HashMap<>();
        params.put("timestamp", "" + System.currentTimeMillis());

        bizData.put("phone", "15901008289");
        bizData.put("identityNo", "43131");
        bizData.put("clientType", "android");
        bizData.put("imei", "imei");
        bizData.put("timestamp", params.get("timestamp").toString());
        params.put("signType", md5WithRSA);
        String jsonData = JsonUtil.toJson(bizData);
        byte[] aesEncrypted = CodecUtil.aesEncrypt(jsonData.getBytes(), aesGenKey);
        params.put("bizData", new String(CodecUtil.base64Encode(aesEncrypted)));
        params.put("bizEnc", "1");
        params.put("appId", "kskd");
        params.put("version", "1.0.0");

        //签名
        params.put("sign", new String(CodecUtil.base64Encode(SignUtil.rsaSign(SignUtil.sortedSign(params).getBytes(), rsaConfig.getPriKey(), md5WithRSA))));
        params.put("desKey", new String(CodecUtil.base64Encode(CodecUtil.rsaEncrypt(rsaConfig.getPriKey(), aesKey))));
        return restTemplate.postForObject("http://localhost:9999/flow/user/check", params, String.class);
    }


}

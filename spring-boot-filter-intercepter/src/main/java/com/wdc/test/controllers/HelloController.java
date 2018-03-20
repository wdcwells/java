package com.wdc.test.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.wdc.test.config.RsaConfig;
import com.wdc.test.utils.CodecUtil;
import com.wdc.test.utils.SignUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("hello")
public class HelloController {
    private static final ObjectMapper mapper = new ObjectMapper();
    @Autowired
    private RsaConfig rsaConfig;

    @GetMapping("txt")
    String getHelloMsg() {
        return "hello";
    }

    @GetMapping("json")
    Map<String, String> getHelloJson() throws Exception {
        String md5WithRSA = "MD5WithRSA";
        Map<String, String> data = new HashMap<>();
        Map<String, String> json = new HashMap<>();
        json.put("key", "value");
        data.put("signType", md5WithRSA);
        data.put("bizData", mapper.writeValueAsString(json));
        data.put("appId", "appId");
        data.put("version", "1.0");
        data.put("timestamp", "" + System.currentTimeMillis());
        data.put("sign",
                new String(CodecUtil.base64Encode(SignUtil.rsaSign(SignUtil.sortedSign(data).getBytes(), rsaConfig.getPriKey(), md5WithRSA))));
        return data;
    }

    @PostMapping("obj")
    Object postHelloObj(@RequestBody ObjectNode objectNode) {
        return objectNode;
    }
}

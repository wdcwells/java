package com.wdc.test.controllers;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.wdc.test.config.RsaConfig;
import com.wdc.test.utils.CodecUtil;
import com.wdc.test.utils.SignUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("hello")
public class HelloController {
    private static final ObjectMapper mapper = new ObjectMapper();
    @Autowired
    private RsaConfig rsaConfig;

    @GetMapping("url")
    String getUrl(@RequestParam String url) {
        return url;
    }

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

    @PostMapping("jsonNode")
    Object postHelloObj(@RequestBody ObjectNode objectNode) {
        return objectNode;
    }

    @PostMapping("pojo")
    Object postPojo(@RequestBody PoJo poJo) {
        return poJo;
        //todo datetimeformatter
    }

    @PostMapping("simple")
    Simple postSimple(Simple simple) {
        return simple;
    }


    private static class Simple {
        private Integer id;
        private String name;
        private int age;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }

    private static class PoJo {
        private int id;
        private String name;
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private Date date = new Date();
        private LocalDateTime localDateTime = LocalDateTime.now();
        private Instant instant = Instant.now();

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public Instant getInstant() {
            return instant;
        }

        public void setInstant(Instant instant) {
            this.instant = instant;
        }

        public LocalDateTime getLocalDateTime() {
            return localDateTime;
        }

        public void setLocalDateTime(LocalDateTime localDateTime) {
            this.localDateTime = localDateTime;
        }
    }


}

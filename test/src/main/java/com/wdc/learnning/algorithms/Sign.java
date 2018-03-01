package com.wdc.learnning.algorithms;

import java.util.*;
import java.util.stream.Collectors;

public class Sign {
    public static String getSign(Map<String, String> params) {
        List<String> keys = new ArrayList<>(params.keySet());
        Collections.sort(keys);
        return keys.stream().map(key -> {
            StringBuilder tmp = new StringBuilder();
            tmp.append(key).append("=").append(params.get(key));
            return tmp.toString();
        }).collect(Collectors.joining("&"));
    }

    public static void main(String[] args) {
        Map<String, String> params = new HashMap<>();
        params.put("appid", "wxd930ea5d5a258f4f");
        params.put("mch_id", "10000100");
        params.put("device_info", "1000");
        params.put("body", "test");
        params.put("nonce_str", "ibuaiVcKdpRxkhJA");
        System.out.println(getSign(params).equals("appid=wxd930ea5d5a258f4f&body=test&device_info=1000&mch_id=10000100&nonce_str=ibuaiVcKdpRxkhJA"));
    }
}

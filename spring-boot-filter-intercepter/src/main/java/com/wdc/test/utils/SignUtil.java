package com.wdc.test.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SignUtil {
    /**
     * 1、参数名按字典排序
     * 2、拼接
     * @param params
     * @return
     */
    public static String sortedSign(Map<String, String> params) {
        List<String> keys = new ArrayList<>(params.keySet());
        Collections.sort(keys);
        return keys.stream().map(key -> {
            StringBuilder tmp = new StringBuilder();
            tmp.append(key).append("=").append(params.get(key));
            return tmp.toString();
        }).collect(Collectors.joining("&"));
    }
}

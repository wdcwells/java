package com.wdc.test.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonUtil {
    private static final ObjectMapper mapper;

    static {
        mapper = new ObjectMapper();
//        mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
    }

    public static void main(String[] args) {
        TestData wdc = new TestData(1, "wdc");
        TestData wqh = new TestData(2, "wqh");

        List<TestData> list = new ArrayList<>();
        list.add(wdc);
        list.add(wqh);

        String json = toJson(list);
        System.out.println(json);

        ArrayNode nodes = toObj(ArrayNode.class, json);

        Map<String, Object> map = new HashMap<>();
        map.put("1", 1);
        map.put("2", "2");

        System.out.println(toJson(map));
    }

    public static String toJson(Object obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static <T> T toObj(Class<T> tClass, String json) {
        try {
            return mapper.readValue(json, tClass);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static JsonNode readTree(String json) {
        try {
            return mapper.readTree(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static class TestData {
        private Integer id;
        private String name;

        public TestData() {
        }

        public TestData(Integer id, String name) {
            this.id = id;
            this.name = name;
        }

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
    }
}

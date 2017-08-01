package com.wdc.learnning.jackson;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

/**
 * Created by wangdachong on 2017/8/1.
 */
public class JsonTest {

    @Test
    public void test(){
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        try {
            System.out.println(mapper.writeValueAsString(new Data(null, "wdc")));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    private class Data {
        private Integer id;
        private String name;

        public Data(Integer id, String name) {
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

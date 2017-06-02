package com.wdc.learnning;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Test
    public void test1() {
        List<JSONObject> jsonObjects = Collections.singletonList(new JSONObject(Collections.singletonMap("hello", "world")));
        jsonObjects.stream().forEach(jsonObject -> {
            try {
                jsonObject.put("hi", "nihao");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        });

        jsonObjects.stream().forEach(System.out::println);
    }

}

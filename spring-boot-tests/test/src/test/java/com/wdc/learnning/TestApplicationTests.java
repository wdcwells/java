package com.wdc.learnning;

import com.wdc.learnning.async.AsyncTask;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestApplicationTests {

    @Autowired
    private AsyncTask asyncTask;

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

    @Test
    public void test2() throws ExecutionException, InterruptedException {
        Future<String> test = asyncTask.test();
        System.out.println("test2");
        while (!test.isDone()) {
            System.out.println("not done");
        }
        System.out.println(test.get());
    }


}

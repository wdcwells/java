package com.wdc.study;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.concurrent.CountDownLatch;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootRedisApplicationTests {
	@Autowired
	private StringRedisTemplate template;
	@Autowired
	private CountDownLatch countDownLatch;
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	@Test
	public void contextLoads() throws InterruptedException {
		template.convertAndSend("chat", "Hello from Redis!");
		countDownLatch.await();
		System.out.println(countDownLatch.getCount());
	}

	@Test
	public void rightPushAll() {
		redisTemplate.opsForList().rightPushAll("wdc-list", Arrays.asList("a","b","c","d"));
	}

}

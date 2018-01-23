package com.wdc.learnning;

import com.wdc.learnning.autowired.TestInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class TestApplication implements CommandLineRunner{
	@Autowired
	private List<TestInterface> testInterfaces;


	public static void main(String[] args) {
		SpringApplication.run(TestApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		testInterfaces.forEach(TestInterface::sayHello);
	}
}

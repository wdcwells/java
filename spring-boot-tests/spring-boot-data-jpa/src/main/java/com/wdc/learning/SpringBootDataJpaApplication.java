package com.wdc.learning;

import com.wdc.learning.entity.Customer;
import com.wdc.learning.entity.TmpUser;
import com.wdc.learning.repository.CustomerRepository;
import com.wdc.learning.repository.TmpUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
public class SpringBootDataJpaApplication implements CommandLineRunner {

	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private TmpUserRepository tmpUserRepository;

	public static void main(String[] args) throws Exception {
		SpringApplication.run(SpringBootDataJpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		testTransactional();
		testAsyncFind();
//		System.exit(0);
	}

	@Async
	public void testAsyncFind() {
		System.out.println(tmpUserRepository.count());
		System.out.println(customerRepository.count());
		System.out.println(customerRepository.findOne(1L).getLastName());
		throw new RuntimeException("error");
	}

	@Transactional
	public void testTransactional() {
		Customer jack = customerRepository.findOne(1L);
		jack.setLastName("Bauer1");
		customerRepository.save(jack);
		saveCustomer();
		saveTmpUser();
	}

	public void saveTmpUser() {
		TmpUser tmpUser = new TmpUser();
		tmpUser.setUserId("userId");
		tmpUser.setIdCard("123");
		tmpUser.setMobile("456");
		tmpUserRepository.save(tmpUser);
	}

	public void saveCustomer() {
		Customer wqh = new Customer();
		wqh.setFirstName("w");
		wqh.setLastName("qh");
		customerRepository.save(wqh);
	}


}

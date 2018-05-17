package com.wdc.learning;

import com.wdc.learning.entity.Customer;
import com.wdc.learning.entity.TmpUser;
import com.wdc.learning.repository.CustomerRepository;
import com.wdc.learning.repository.TmpUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
//		System.exit(0);
	}

	public void testTransactional() {
		saveCustomer();
		saveTmpUser();
		System.out.println(customerRepository.findAll().get(0));
		System.out.println(tmpUserRepository.findAll().get(0));
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

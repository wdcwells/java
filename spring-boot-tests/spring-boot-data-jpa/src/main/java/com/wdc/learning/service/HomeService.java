package com.wdc.learning.service;

import com.wdc.learning.entity.Customer;
import com.wdc.learning.entity.TmpUser;
import com.wdc.learning.repository.CustomerRepository;
import com.wdc.learning.repository.TmpUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

/**
 * @author wdc
 * @date 2018/5/30
 */
@Service
public class HomeService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private TmpUserRepository tmpUserRepository;

    public Customer findOne(Long id) {
        return customerRepository.findOne(id);
    }

    @Async
    public void testAsyncFind(){
        System.out.println(tmpUserRepository.count());
        System.out.println(customerRepository.count());
        System.out.println(customerRepository.findOne(1L).getLastName());
        throw new RuntimeException("error");
    }

    @Async
    @Transactional
    public void testTransactional() throws InterruptedException {
        Thread.sleep(10 * 1000);
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

    private void testUpdateField() {
        customerRepository.updateLastNameById(Collections.singletonList(1L), "name");
    }
}

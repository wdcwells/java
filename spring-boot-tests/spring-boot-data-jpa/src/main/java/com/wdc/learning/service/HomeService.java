package com.wdc.learning.service;

import com.wdc.learning.entity.Customer;
import com.wdc.learning.entity.TmpUser;
import com.wdc.learning.repository.CustomerRepository;
import com.wdc.learning.repository.TmpUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private AsyncService asyncService;
    @Autowired
    private TxHelperService txHelperService;

    public Customer findOne(Long id) {
        return customerRepository.findById(id).get();
    }

//    @Async
    public void testAsyncFind(){
        System.out.println(tmpUserRepository.count());
        System.out.println(customerRepository.count());
        System.out.println(customerRepository.findById(1L).get().getLastName());
        throw new RuntimeException("error");
    }

//    @Async
    @Transactional
    public void testTransactional() throws Exception {
        Thread.sleep(5 * 1000);
        Customer jack = customerRepository.findById(1L).get();
        jack.setLastName("Bauer1");
        customerRepository.save(jack);
        saveCustomer();
        saveTmpUser();
    }

//    @Transactional
    public void testRepoTx() {
        txHelperService.txTestRepoTx(this);
//        internalTestRepoTx();
    }

    public void internalTestRepoTx() {
        testUpdateField();
        customerRepository.deleteById(11L);
    }

    private void saveTmpUser() {
        TmpUser tmpUser = new TmpUser();
        tmpUser.setUserId("userId");
        tmpUser.setIdCard("123");
        tmpUser.setMobile("456");
        tmpUserRepository.save(tmpUser);
    }

    private void saveCustomer() {
        Customer wqh = new Customer();
        wqh.setFirstName("w");
        wqh.setLastName("qh");
        customerRepository.save(wqh);
    }

    private void testUpdateField() {
        customerRepository.updateLastNameById(Collections.singletonList(10L), "name");
    }
}


package com.wdc.learning.service;

import com.wdc.learning.entity.Customer;
import com.wdc.learning.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author wdc
 * @date 2018/8/24
 */
@Service
public class TxService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ApplicationEventPublisher eventPublisher;

    public List<Customer> list() {
        List<Customer> list = customerRepository.findAll();
        return list;
    }

    @Transactional
    public Customer save(Customer customer) {
        eventPublisher.publishEvent(new ApplicationEvent(customer){});
        customerRepository.save(customer);
        if (true) throw new RuntimeException("123");//test rollback
        return customer;
    }
}

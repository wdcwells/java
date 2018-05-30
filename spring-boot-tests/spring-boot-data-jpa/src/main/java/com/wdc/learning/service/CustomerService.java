package com.wdc.learning.service;

import com.wdc.learning.entity.Customer;
import com.wdc.learning.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wdc
 * @date 2018/5/30
 */
@Service
public class CustomerService {
    @Autowired
    private CustomerRepository repository;

    public Customer findOne(Long id) {
        return repository.findOne(id);
    }
}

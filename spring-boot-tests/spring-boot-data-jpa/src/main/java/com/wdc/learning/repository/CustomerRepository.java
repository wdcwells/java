package com.wdc.learning.repository;

import com.wdc.learning.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author wdc
 * @date 2018/5/16
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}

package com.wdc.learning.repository;

import com.wdc.learning.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author wdc
 * @date 2018/5/16
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Transactional
    @Modifying
    @Query("update Customer a set a.lastName = ?2 where a.id in ?1")
    void updateLastNameById(List<Long> id, String lastName);

    void deleteById(Long id);
}

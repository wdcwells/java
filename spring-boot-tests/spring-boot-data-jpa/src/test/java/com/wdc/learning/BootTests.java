package com.wdc.learning;

import com.wdc.learning.entity.Customer;
import com.wdc.learning.service.TxService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author wdc
 * @date 2018/8/24
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class BootTests {
    @Autowired
    private TxService txService;

    @Test
    public void list() {
        System.out.println(txService.list());
    }

    @Test
    public void save() {
        Customer customer = new Customer();
        customer.setFirstName("www");
        customer.setLastName("baidu");
        txService.save(customer);
    }
}

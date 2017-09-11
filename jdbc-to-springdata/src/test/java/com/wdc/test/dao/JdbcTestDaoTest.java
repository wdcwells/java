package com.wdc.test.dao;

import com.wdc.test.entity.JdbcTest;
import org.junit.Test;

public class JdbcTestDaoTest {
    @Test
    public void findAll() throws Exception {
        System.out.println(new JdbcTestDao1().findAll());
    }

    @Test
    public void save() throws Exception {
        System.out.println(new JdbcTestDao1().save(new JdbcTest("test102", 102)));
    }

}
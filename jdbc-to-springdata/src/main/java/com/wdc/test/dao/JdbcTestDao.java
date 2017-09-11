package com.wdc.test.dao;

import com.wdc.test.entity.JdbcTest;

import java.util.List;

public interface JdbcTestDao {
    List<JdbcTest> findAll();
    JdbcTest save(JdbcTest jdbcTest);
}

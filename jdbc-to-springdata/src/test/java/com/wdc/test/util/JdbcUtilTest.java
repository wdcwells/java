package com.wdc.test.util;

import org.junit.Test;

import java.sql.Connection;

import static org.junit.Assert.*;

public class JdbcUtilTest {
    @Test
    public void getConnection() throws Exception {
        Connection connection = JdbcUtil.getConnection();
        assert null != connection;
    }

}
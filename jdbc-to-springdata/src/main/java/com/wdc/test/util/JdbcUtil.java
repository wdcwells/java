package com.wdc.test.util;

import java.sql.*;

public class JdbcUtil {

    public static Connection getConnection() throws Exception {
        String jdbcDriver = "com.mysql.jdbc.Driver";
        Class.forName(jdbcDriver);
        String url = "jdbc:mysql:///test?user=root&password=mysql&characterEncoding=utf8";
        Connection connection = DriverManager.getConnection(url);
        return connection;
    }

    public static void release(ResultSet resultSet, Statement statement, Connection connection) {
        if (null != resultSet) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (null != statement) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (null != connection) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

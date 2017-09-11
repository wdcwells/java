package com.wdc.test.dao;

import com.wdc.test.entity.JdbcTest;
import com.wdc.test.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcTestDao1 implements JdbcTestDao{

    public List<JdbcTest> findAll() {
        List<JdbcTest> result = new ArrayList<JdbcTest>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = JdbcUtil.getConnection();
            String sql = "SELECT * from jdbc_test";
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            JdbcTest jdbcTest;
            while (resultSet.next()) {
                jdbcTest = new JdbcTest(resultSet.getString("name"), resultSet.getInt("age"));
                jdbcTest.setId(resultSet.getInt("id"));
                result.add(jdbcTest);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.release(resultSet, statement, connection);
        }
        return result;
    }

    public JdbcTest save(JdbcTest jdbcTest) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = JdbcUtil.getConnection();
            connection.setAutoCommit(false);
            String sql = "INSERT INTO jdbc_test(name,age) values(?,?)";
            statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, jdbcTest.getName());
            statement.setInt(2, jdbcTest.getAge());
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                jdbcTest.setId(resultSet.getInt(1));
            }
            connection.commit();
        } catch (Exception e) {
            if (null != connection) {
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            JdbcUtil.release(resultSet, statement, connection);
        }
        return jdbcTest;
    }
}

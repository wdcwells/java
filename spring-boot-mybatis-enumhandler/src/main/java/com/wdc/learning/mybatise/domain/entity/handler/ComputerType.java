package com.wdc.learning.mybatise.domain.entity.handler;

import com.wdc.learning.mybatise.domain.entity.Computer;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by wangdachong on 2017/7/28.
 */
public class ComputerType extends BaseTypeHandler<Computer.TypeEnum> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Computer.TypeEnum parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, parameter.getCode());
    }

    @Override
    public Computer.TypeEnum getNullableResult(ResultSet rs, String columnName) throws SQLException {
        Integer s = rs.getInt(columnName);
        return s == null ? null : Computer.TypeEnum.getByCode(s);
    }

    @Override
    public Computer.TypeEnum getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        Integer s = rs.getInt(columnIndex);
        return s == null ? null : Computer.TypeEnum.getByCode(s);
    }

    @Override
    public Computer.TypeEnum getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        Integer s = cs.getInt(columnIndex);
        return s == null ? null : Computer.TypeEnum.getByCode(s);
    }
}

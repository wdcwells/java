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
public class ComputerBrand extends BaseTypeHandler<Computer.BrandEnum> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Computer.BrandEnum parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getName());
    }

    @Override
    public Computer.BrandEnum getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String s = rs.getString(columnName);
        return s == null ? null : Computer.BrandEnum.getByName(s);
    }

    @Override
    public Computer.BrandEnum getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String s = rs.getString(columnIndex);
        return s == null ? null : Computer.BrandEnum.getByName(s);
    }

    @Override
    public Computer.BrandEnum getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String s = cs.getString(columnIndex);
        return s == null ? null : Computer.BrandEnum.getByName(s);
    }
}

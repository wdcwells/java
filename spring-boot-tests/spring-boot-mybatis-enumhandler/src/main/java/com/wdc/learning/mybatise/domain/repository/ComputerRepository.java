package com.wdc.learning.mybatise.domain.repository;

import com.wdc.learning.mybatise.domain.Page;
import com.wdc.learning.mybatise.domain.entity.Computer;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.StatementType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wangdachong on 2017/7/28.
 */
@Mapper
@Repository
public interface ComputerRepository {
    @Insert("insert into computer(name,brand,type,scale)" +
            " values(#{name},#{brand},#{type},#{scale})")
    @SelectKey(statement = "SELECT last_insert_id() as id", keyProperty = "id", before = false, resultType = Integer.class, statementType = StatementType.STATEMENT)
    void save(Computer computer);

    @Select("select * from computer")
    List<Computer> getList();

    @Select("select * from computer where id=#{id}")
    Computer geOne(@Param("id") Integer id);

    @Select("select count(1) from computer")
    long count();

    List<Computer> getPage(Page page);
}



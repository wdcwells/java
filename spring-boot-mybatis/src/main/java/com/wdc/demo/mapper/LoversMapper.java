package com.wdc.demo.mapper;

import com.wdc.demo.domain.model.Lovers;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by wangdachong on 2017/4/15.
 */
@Mapper
public interface LoversMapper {
    @Select("select * from demo_lovers")
    List<Lovers> findAll();

    @Select("select * from demo_lovers where id=#{id}")
    Lovers findOne(String id);

    @Insert("insert into demo_lovers(id,name,boy_id,girl_id,create_time,update_time)" +
            " value(#{id},#{name},#{boy_id},#{girl_id},#{create_time},#{update_time})")
    void save(Lovers lovers);

    @Update("update demo_lovers set" +
            " name=#{name},boy_id=#{boy_id},girl_id=#{girl_id},update_time=#{update_time}" +
            " where id=#{id}")
    void update(Lovers lovers);

    @Delete("delete from demo_lovers where id=#{id}")
    void delete(String id);
}

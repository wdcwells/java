package com.wdc.demo.mapper;

import com.wdc.demo.domain.model.Young;
import com.wdc.demo.domain.query.YoungQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * Created by wangdachong on 2017/4/15.
 */
@Mapper
public interface YoungMapper {
    @Select("select * from demo_young")
    List<Young> findAll();

    List<Young> findByIds(Integer[] ids);

    //分页
    long searchCount(YoungQuery query);
    List<Young> searchPage(Map map);

    void save(Young young);
    void update(Young young);
    void deleteByIds(Integer[] ids);
}

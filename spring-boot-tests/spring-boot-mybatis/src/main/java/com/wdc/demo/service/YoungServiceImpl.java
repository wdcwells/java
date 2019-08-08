package com.wdc.demo.service;

import com.wdc.demo.domain.model.Young;
import com.wdc.demo.domain.query.YoungQuery;
import com.wdc.demo.mapper.YoungMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangdachong on 2017/4/15.
 */
@Service
public class YoungServiceImpl implements YoungService{
    @Autowired private YoungMapper youngMapper;

    @Override
    public List<Young> findAll() {
        return youngMapper.findAll();
    }

    @Override
    public List<Young> findByIds(Integer[] ids) {
        return youngMapper.findByIds(ids);
    }

    @Override
    public List<Young> findByExample(Young example) {
        return youngMapper.findByExample(example);
    }

    @Override
    public long searchCount(YoungQuery query) {
        return youngMapper.searchCount(query);
    }

    @Override
    public Page<Young> findPage(Pageable pageable, YoungQuery query) {
        Map<String, Object> map = new HashMap();
        map.put("offset", pageable.getOffset());
        map.put("size", pageable.getPageSize());
        map.put("sort", pageable.getSort());
        map.put("query", query);
        List<Young> content = youngMapper.searchPage(map);
        return new PageImpl<>(content, pageable, youngMapper.searchCount(query));
    }

    @Override
    public void save(Young young) {
        youngMapper.save(young);
    }

    @Override
    public void update(Young young) {
        youngMapper.update(young);
    }

    @Override
    public void deleteByIds(Integer[] ids) {
        youngMapper.deleteByIds(ids);
    }
}

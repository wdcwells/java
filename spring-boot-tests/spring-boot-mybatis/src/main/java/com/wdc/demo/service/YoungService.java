package com.wdc.demo.service;

import com.wdc.demo.domain.model.Young;
import com.wdc.demo.domain.query.YoungQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by wangdachong on 2017/4/15.
 */
public interface YoungService {
    List<Young> findAll();
    List<Young> findByIds(Integer[] ids);
    long searchCount(YoungQuery query);
    Page<Young> findPage(Pageable pageable, YoungQuery query);

    void save(Young young);
    void update(Young young);
    void deleteByIds(Integer[] id);
}

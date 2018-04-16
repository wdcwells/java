package com.wdc.demo.service;

import com.wdc.demo.domain.model.Lovers;

import java.util.List;

/**
 * Created by wangdachong on 2017/4/15.
 */
public interface LoversService {
    List<Lovers> findAll();
    Lovers findOne(String id);
    void save(Lovers lovers);
    void update(Lovers lovers);
    void delete(String id);
}

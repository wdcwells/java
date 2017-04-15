package com.gocom.demo.service;

import com.gocom.demo.domain.model.Lovers;

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

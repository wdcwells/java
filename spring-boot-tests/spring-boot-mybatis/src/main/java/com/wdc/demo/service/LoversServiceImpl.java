package com.wdc.demo.service;

import com.wdc.demo.domain.model.Lovers;
import com.wdc.demo.mapper.LoversMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wangdachong on 2017/4/15.
 */
@Service
public class LoversServiceImpl implements LoversService {
    @Autowired private LoversMapper loversMapper;

    @Override
    public List<Lovers> findAll() {
        return loversMapper.findAll();
    }

    @Override
    public Lovers findOne(String id) {
        return loversMapper.findOne(id);
    }

    @Override
    public void save(Lovers lovers) {
        loversMapper.save(lovers);
    }

    @Override
    public void update(Lovers lovers) {
        loversMapper.update(lovers);
    }

    @Override
    public void delete(String id) {
        loversMapper.delete(id);
    }
}

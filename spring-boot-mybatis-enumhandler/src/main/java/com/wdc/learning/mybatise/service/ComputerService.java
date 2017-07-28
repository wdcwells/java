package com.wdc.learning.mybatise.service;

import com.wdc.learning.mybatise.domain.entity.Computer;

import java.util.List;

/**
 * Created by wangdachong on 2017/7/28.
 */
public interface ComputerService {
    Computer save(Computer computer);
    List<Computer> getList();
    Computer getOne(Integer id);
}

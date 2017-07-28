package com.wdc.learning.mybatise.service.impl;

import com.wdc.learning.mybatise.domain.entity.Computer;
import com.wdc.learning.mybatise.domain.repository.ComputerRepository;
import com.wdc.learning.mybatise.service.ComputerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wangdachong on 2017/7/28.
 */
@Service
public class ComputerServiceImpl implements ComputerService {

    @Autowired
    private ComputerRepository computerRepository;

    @Override
    public Computer save(Computer computer) {
        computerRepository.save(computer);
        return computer;
    }

    @Override
    public List<Computer> getList() {
        return computerRepository.getList();
    }

    @Override
    public Computer getOne(Integer id) {
        return computerRepository.geOne(id);
    }
}

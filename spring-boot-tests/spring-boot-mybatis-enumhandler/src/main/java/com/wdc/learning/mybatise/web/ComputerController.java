package com.wdc.learning.mybatise.web;

import com.wdc.learning.mybatise.domain.Page;
import com.wdc.learning.mybatise.domain.entity.Computer;
import com.wdc.learning.mybatise.service.ComputerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.web.bind.annotation.*;

/**
 * Created by wangdachong on 2017/7/28.
 */
@RestController
@RequestMapping("computer")
public class ComputerController {
    @Autowired
    private ComputerService computerService;

    @PostMapping
    public Object save(@RequestBody Computer computer) {
        return computerService.save(computer);
    }

    @GetMapping("list")
    public Object getList() {
        return computerService.getList();
    }

    @GetMapping("page")
    public Object getPage(@SortDefault(sort = {"id"}, direction = Sort.Direction.DESC) Sort springSort) {
        Page<Object> page = new Page<>();
        page.setSort(springSort);
        return computerService.getPage(page);
    }

    @GetMapping("{id}")
    public Object getOne(@PathVariable Integer id){
        return computerService.getOne(id);
    }
}

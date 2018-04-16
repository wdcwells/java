package com.wdc.demo.controller;

import com.wdc.demo.domain.model.Lovers;
import com.wdc.demo.service.LoversService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * Created by wangdachong on 2017/4/15.
 */
@RestController
@RequestMapping("demo/lovers")
public class LoversController {
    @Autowired private LoversService loversService;

    @RequestMapping("all")
    public List<Lovers> selectAll() {
        return loversService.findAll();
    }

    @RequestMapping
    public Lovers findOne(String id, HttpServletResponse response) throws IOException{
        Lovers lovers = null;
        if (id == null || id.trim().length() == 0){
            response.sendError(400, "主键不能为空！");
        } else {
            try {
                lovers = loversService.findOne(id);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (null == lovers) {
            response.sendError(404, "主键不存在！");
        }
        return lovers;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Lovers save(@RequestBody Lovers lovers){
        try {
            lovers.setId(UUID.randomUUID().toString().replaceAll("-", ""));
            loversService.save(lovers);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lovers;
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Lovers update(@RequestBody Lovers lovers, HttpServletResponse response) throws IOException{
        if (lovers.getId() == null || lovers.getId().trim().length() == 0){
            response.sendError(400, "主键不能为空！");
        } else {
            try {
                loversService.update(lovers);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return lovers;
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public int delete(String id) throws IOException{
        int result = 0;
        try {
            loversService.delete(id);
            result = 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}

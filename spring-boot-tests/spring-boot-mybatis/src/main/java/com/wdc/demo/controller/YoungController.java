package com.wdc.demo.controller;

import com.wdc.demo.domain.model.Young;
import com.wdc.demo.domain.query.YoungQuery;
import com.wdc.demo.service.YoungService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by wangdachong on 2017/4/15.
 */
@RestController
@RequestMapping("demo/young")
public class YoungController {
    @Autowired
    private YoungService youngService;

    @RequestMapping("all")
    public List<Young> findAll() {
        return youngService.findAll();
    }

    @RequestMapping("in")
    public List<Young> findByIds(Integer[] ids) {
        return youngService.findByIds(ids);
    }

    @RequestMapping("some")
    public List<Young> findByExample(Young example) {
        return youngService.findByExample(example);
    }

    @RequestMapping(value = "page")
    public Page<Young> searchPage(Pageable pageable, YoungQuery query) {
        return youngService.findPage(pageable, query);
    }

    @RequestMapping("count")
    public long searchCount(YoungQuery query) {
        return youngService.searchCount(query);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Young save(@RequestBody Young young) {
        try {
            String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            young.setCreate_time(date);
            youngService.save(young);
            List<Young> byIds = youngService.findByIds(new Integer[]{young.getId()});
            if (byIds != null && !byIds.isEmpty()) return byIds.get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return young;
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Young update(@RequestBody Young young, HttpServletResponse response) throws IOException {
        if (young.getId() == null || young.getId().equals(0)) {
            response.sendError(400, "主键不能为空！");
        } else {
            try {
                young.setUpdate_time(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                youngService.update(young);
                List<Young> byIds = youngService.findByIds(new Integer[]{young.getId()});
                if (byIds != null && !byIds.isEmpty()) return byIds.get(0);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return young;
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public int deleteByIds(Integer[] ids) {
        int result = 0;
        try {
            youngService.deleteByIds(ids);
            result = 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}

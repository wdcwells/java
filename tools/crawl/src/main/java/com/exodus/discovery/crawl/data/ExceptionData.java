package com.exodus.discovery.crawl.data;

import org.springframework.data.mongodb.core.index.Indexed;

/**
 * Created by wangdachong on 2017/3/15.
 */
public class ExceptionData {
    @Indexed
    private String name;
    private String detailMsg;

    public ExceptionData(String name, String detailMsg) {
        this.name = name;
        this.detailMsg = detailMsg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetailMsg() {
        return detailMsg;
    }

    public void setDetailMsg(String detailMsg) {
        this.detailMsg = detailMsg;
    }
}

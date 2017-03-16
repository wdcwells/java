package com.exodus.discovery.crawl.data;

import org.springframework.data.mongodb.core.index.Indexed;

/**
 * Created by wangdachong on 2017/3/15.
 */
public class PageId {
    @Indexed
    private String IdName;
    @Indexed
    private String IdValue;

    public PageId(String idName, String idValue) {
        IdName = idName;
        IdValue = idValue;
    }

    public String getIdName() {
        return IdName;
    }

    public void setIdName(String idName) {
        IdName = idName;
    }

    public String getIdValue() {
        return IdValue;
    }

    public void setIdValue(String idValue) {
        IdValue = idValue;
    }
}

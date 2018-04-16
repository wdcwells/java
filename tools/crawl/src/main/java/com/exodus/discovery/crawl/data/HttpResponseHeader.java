package com.exodus.discovery.crawl.data;

/**
 * Created by wangdachong on 2017/3/15.
 */
public class HttpResponseHeader {
    public String headerName;
    public String headerValue;

    public HttpResponseHeader(String headerName, String headerValue) {
        this.headerName = headerName;
        this.headerValue = headerValue;
    }

    @Override
    public String toString() {
        return "HttpResponseHeader{" +
                "headerName='" + headerName + '\'' +
                ", headerValue='" + headerValue + '\'' +
                '}';
    }
}

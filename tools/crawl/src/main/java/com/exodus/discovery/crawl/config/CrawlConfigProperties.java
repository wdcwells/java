package com.exodus.discovery.crawl.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by wangdachong on 2017/3/15.
 */
@ConfigurationProperties(prefix = "crawl")
public class CrawlConfigProperties {

    /**
     * 采集源头
     * https://www.baidu.com/##NUM##
     */
    private String sourcePattern;

    /* 采集循环开始 */
    private int startNum;

    /* 采集循环结束 */
    private int endNum;

    /* cookie 绝对路径 */
    private String httpHeadersFromFile;

    /* 采集间隔(秒) */
    private int intervals;

    public String getSourcePattern() {
        return sourcePattern;
    }

    public void setSourcePattern(String sourcePattern) {
        this.sourcePattern = sourcePattern;
    }

    public void setStartNum(int startNum) {
        this.startNum = startNum;
    }

    public void setEndNum(int endNum) {
        this.endNum = endNum;
    }

    public int getStartNum() {
        return startNum;
    }

    public int getEndNum() {
        return endNum;
    }

    public String getHttpHeadersFromFile() {
        return httpHeadersFromFile;
    }

    public void setHttpHeadersFromFile(String httpHeadersFromFile) {
        this.httpHeadersFromFile = httpHeadersFromFile;
    }

    public int getIntervals() {
        return intervals;
    }

    public void setIntervals(int intervals) {
        this.intervals = intervals;
    }
}

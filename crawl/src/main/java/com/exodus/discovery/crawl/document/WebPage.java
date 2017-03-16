package com.exodus.discovery.crawl.document;

import com.exodus.discovery.crawl.data.ExceptionData;
import com.exodus.discovery.crawl.data.HttpResponseHeader;
import com.exodus.discovery.crawl.data.PageId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangdachong on 2017/3/15.
 * a response page from internet with its url and other things for record
 */
@Document(collection = "webpage")
public class WebPage {
    @Id
    private String id;

    /* 资源url */
    @Indexed
    private String pageUrl;

    /* 来源 url */
    @Indexed
    private String sourceUrl;

    /* 响应状态码 */
    @Indexed
    private int responseStatus = 0;

    /* 响应体 */
    private String responseEntity = "出现异常";

    /* 是否解析 */
    @Indexed
    private int isParsed = 0;

    private List<HttpResponseHeader> headers = new ArrayList<>();

    /* 其他页面标志性id 如：公司id*/
    private List<PageId> pageIds = new ArrayList<>();

    /* 响应错误 */
    private List<ExceptionData> exceptions = new ArrayList<>();

    private long createdTime = System.currentTimeMillis();

    private long updatedTime = System.currentTimeMillis();

    public WebPage(String pageUrl, String sourceUrl) {
        this.pageUrl = pageUrl;
        this.sourceUrl = sourceUrl;
    }

    public String getResponseEntity() {
        return responseEntity;
    }

    public void setResponseEntity(String responseEntity) {
        this.responseEntity = responseEntity;
    }

    public List<PageId> getPageIds() {
        return pageIds;
    }

    public void setPageIds(List<PageId> pageIds) {
        this.pageIds = pageIds;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPageUrl() {
        return pageUrl;
    }

    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    public int getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(int responseStatus) {
        this.responseStatus = responseStatus;
    }

    public int getIsParsed() {
        return isParsed;
    }

    public void setIsParsed(int isParsed) {
        this.isParsed = isParsed;
    }

    public List<HttpResponseHeader> getHeaders() {
        return headers;
    }

    public void setHeaders(List<HttpResponseHeader> headers) {
        this.headers = headers;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public long getCreatedTime() {
        return createdTime;
    }

    public long getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(long updatedTime) {
        this.updatedTime = updatedTime;
    }

    public List<ExceptionData> getExceptions() {
        return exceptions;
    }

    public void setExceptions(List<ExceptionData> exceptions) {
        this.exceptions = exceptions;
    }

    @Override
    public String toString() {
        return "WebPage{" +
                "id='" + id + '\'' +
                ", pageUrl='" + pageUrl + '\'' +
                ", sourceUrl='" + sourceUrl + '\'' +
                ", responseStatus=" + responseStatus +
                ", isParsed=" + isParsed +
                ", headers=" + headers +
                '}';
    }
}

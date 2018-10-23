package com.wdc.study.utils;

import com.google.common.base.Charsets;
import com.google.common.io.ByteStreams;
import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by wdc on 2018/10/23.
 */
public class HttpUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpUtil.class);

    private static final int CONN_TIMEOUT = 2000;
    private static final int READ_TIMEOUT = 10000;
    private static final int MAX_CONNECTION_PER_HOST = 40;
    private static final HttpUtil DEFAULT_HTTP = new HttpUtil();
    private static final ResponseHandler<String> RESPONSE_HANDLER = (response) -> {
        StatusLine statusLine = response.getStatusLine();
        HttpEntity entity = response.getEntity();
        LOGGER.info("statusLine:{}.",statusLine);
        if (statusLine.getStatusCode() >= 300) {
            throw new HttpResponseException(statusLine.getStatusCode(), statusLine.getReasonPhrase());
        }
        if (entity == null) {
            throw new ClientProtocolException("Response contains no content");
        }

        final String result = new String(ByteStreams.toByteArray(entity.getContent()), Charsets.UTF_8);
        LOGGER.info("result:{}.",result);
        return result;
    };
    private CloseableHttpClient httpClient;

    public void setHttpClient(CloseableHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    private HttpUtil() {
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(READ_TIMEOUT)
                .setConnectTimeout(CONN_TIMEOUT)
//                .setProxy(new HttpHost("localhost", 6666))
                .build();
        this.httpClient = HttpClients.custom().setMaxConnPerRoute(MAX_CONNECTION_PER_HOST)
                .setDefaultRequestConfig(requestConfig).build();
    }

    public static HttpUtil getInstance() {
        return DEFAULT_HTTP;
    }

    public String postJson(String url, String json) throws HttpException {
        return post(url, json, ContentType.APPLICATION_JSON);
    }

    public String postXml(String url, String xml) throws HttpException {
        return post(url, xml, ContentType.TEXT_XML);
    }

    public String post(String url, String content, ContentType contentType) throws HttpException {
        try {
            LOGGER.info("post with url:{},content:{}.", url, content);
            final HttpEntity httpEntity = EntityBuilder.create().setContentEncoding(Consts.UTF_8.name())
                    .setContentType(contentType).setText(content).build();

            HttpPost httpPost = new HttpPost(url);
            httpPost.setEntity(httpEntity);
            return this.httpClient.execute(httpPost, RESPONSE_HANDLER);
        } catch (Exception e) {
            LOGGER.error("error in post with url:{},content:{}.", url, content, e);
            throw new HttpException(e.getMessage(), e);
        }
    }

    public String post(String url, Map<String, String> params) throws HttpException {
        try {
            LOGGER.info("post with url:{},params:{}.", url, params);
            List<NameValuePair> nameValuePairs = new ArrayList<>();
            params.forEach((key, value) -> nameValuePairs.add(new BasicNameValuePair(key, value)));
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(nameValuePairs, Consts.UTF_8);
            HttpPost httpPost = new HttpPost(url);
            httpPost.setEntity(entity);
            return this.httpClient.execute(httpPost, RESPONSE_HANDLER);
        } catch (Exception e) {
            LOGGER.error("error in post with url:{},params:{}.", url, params, e);
            throw new HttpException(e.getMessage(), e);
        }
    }

    public String get(String url) throws HttpException {
        try {
            LOGGER.info("get with url:{}.", url);
            HttpGet httpGet = new HttpGet(url);
            return this.httpClient.execute(httpGet, RESPONSE_HANDLER);
        } catch (IOException e) {
            LOGGER.error("error in get with url:{}.", url, e);
            throw new HttpException(e.getMessage(), e);
        }
    }
}

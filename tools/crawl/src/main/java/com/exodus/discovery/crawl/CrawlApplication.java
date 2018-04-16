package com.exodus.discovery.crawl;

import com.exodus.discovery.crawl.config.CrawlConfigProperties;
import com.exodus.discovery.crawl.data.ExceptionData;
import com.exodus.discovery.crawl.data.HttpResponseHeader;
import com.exodus.discovery.crawl.data.PageId;
import com.exodus.discovery.crawl.document.WebPage;
import com.exodus.discovery.crawl.repository.WebPageRepository;
import com.exodus.discovery.crawl.util.FileUtil;
import com.exodus.discovery.crawl.util.SoundsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Properties;
import java.util.stream.Collectors;

@SpringBootApplication
@EnableConfigurationProperties(CrawlConfigProperties.class)
public class CrawlApplication implements CommandLineRunner {

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    WebPageRepository repository;
    @Autowired
    CrawlConfigProperties properties;

    public static void main(String[] args) {
        SpringApplication.run(CrawlApplication.class, args);
    }


    @Override
    public void run(String... args) {
        //--crawl.startNum=2845 --crawl.endNum=20000 --crawl.httpHeadersFromFile=E:\\jars\\headers.properties
        System.out.print("抓取参数：");
        String sourcePattern = properties.getSourcePattern();
        int startNum = properties.getStartNum();
        int endNum = properties.getEndNum();
        int intervals = properties.getIntervals();
        String headersAbsoluteFile = properties.getHttpHeadersFromFile();
        System.out.println(sourcePattern + "," + startNum + "," + endNum + "," + intervals + "," + headersAbsoluteFile);

        if (startNum >= endNum || null == sourcePattern
                || sourcePattern.length() == 8 || sourcePattern.indexOf("##NUM##") < 0) return;

        int endIndex1 = sourcePattern.indexOf("/", 8);
        int endIndex2 = sourcePattern.indexOf("?");
        String rootUrl = sourcePattern.substring(0, endIndex1 == -1 ? (endIndex2 == -1 ? sourcePattern.length() : endIndex2) : endIndex1);

        for (int i = startNum; i < endNum; i++) {
            String pageUrl = sourcePattern.replace("##NUM##", i + "");
            WebPage webPage = new WebPage(pageUrl, rootUrl);
            webPage.getPageIds().add(new PageId("sourceId", i + ""));
            try {
                ResponseEntity<String> httpEntity;
                if (null == headersAbsoluteFile) {
                    httpEntity = restTemplate.getForEntity(pageUrl, String.class);
                } else {
                    final HttpHeaders httpHeaders = new HttpHeaders();
                    try {
                        Properties properties = FileUtil.loadProperties(headersAbsoluteFile, true);
                        if (!properties.isEmpty()) {
                            properties.forEach((o, o2) -> httpHeaders.set(o.toString().trim(), o2.toString().trim()));
                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    httpEntity = restTemplate.exchange(pageUrl, HttpMethod.GET, new HttpEntity(httpHeaders), String.class);
                }

                if (null != httpEntity) {
                    webPage.setResponseEntity(httpEntity.getBody());
                    webPage.setResponseStatus(httpEntity.getStatusCodeValue());
                    httpEntity.getHeaders().forEach((strKey, listStrValue) -> {
                        webPage.getHeaders().add(new HttpResponseHeader(strKey,
                                listStrValue.stream().collect(Collectors.joining("##MYSEPERATOR##"))));
                    });
                }

                repository.save(webPage);
            } catch (Exception e) {
                webPage.getExceptions().add(new ExceptionData(e.getClass().getName(), e.getMessage()));
                if (e.getMessage().contains("I/O error")) {
                    System.out.println("error at " + i);
                    try {
                        SoundsUtil.warn();
                    } catch (Exception e1) {
                    } finally {
                        return;
                    }
                } else {
                    repository.save(webPage);
                }
            }

            try {
                Thread.sleep(intervals*1000);
                intervals = (int) Math.random()*2 + 2;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
}

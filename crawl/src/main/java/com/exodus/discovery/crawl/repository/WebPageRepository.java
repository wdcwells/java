package com.exodus.discovery.crawl.repository;

import com.exodus.discovery.crawl.document.WebPage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by wangdachong on 2017/3/15.
 */
@Repository
public interface WebPageRepository extends MongoRepository<WebPage, String> {

    WebPage findByPageUrl(String pageUrl);

    WebPage[] findBySourceUrl(String sourceUrl);
}

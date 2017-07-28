package com.wdc.learning.mybatise.domain;

import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * Created by wangdachong on 2017/7/28.
 */
public class Page<T> {
    private int pgIndex = 0;
    private int pgCount = 10;
    private Long totalCount = 0L;
    private List<T> elements;
    private Sort sort;

    public int getPgIndex() {
        return pgIndex;
    }

    public void setPgIndex(int pgIndex) {
        this.pgIndex = pgIndex;
    }

    public int getPgCount() {
        return pgCount;
    }

    public void setPgCount(int pgCount) {
        this.pgCount = pgCount;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getElements() {
        return elements;
    }

    public void setElements(List<T> elements) {
        this.elements = elements;
    }

    public Sort getSort() {
        return sort;
    }

    public void setSort(Sort sort) {
        this.sort = sort;
    }
}

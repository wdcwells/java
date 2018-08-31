package com.wdc.test.service;

/**
 * @author wdc
 * @date 2018/8/30
 */
public interface IFirstServiceRetry {
    int retryExample2(FirstService firstService) throws Exception;
    int retryExample2(Exception ex);
}

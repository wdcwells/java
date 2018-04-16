package com.wdc.learnning.async;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

@Component
public class AsyncTask {

    /**
     * 异步方法必须单独拎出来（具体原因 待查明）
     * @return
     * @throws InterruptedException
     */
    @Async
    public Future<String> test() throws InterruptedException {
        Thread.sleep(1000);
        System.out.println("test start");
        return new AsyncResult<>("test finish");
    }
}

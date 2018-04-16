package com.wdc.learnning.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class AsyncConfig implements AsyncConfigurer {
    private static final Logger logger = LoggerFactory.getLogger(AsyncConfig.class);

    /**
     * tasks ：每秒的任务数，假设为50~100
     * taskcost：每个任务花费时间，假设为0.1s
     * responsetime：系统允许容忍的最大响应时间，假设为1s
     * CorePoolSize = min(tasks/(1/taskcost)) = 5
     * QueueCapacity = (CorePoolSize/taskcost)*responsetime = 5
     * MaxPoolSize = (max(tasks)- QueueCapacity)/(1/taskcost) = 9.5 ≈ 9
     *
     * @return
     */
    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(9);
        executor.setQueueCapacity(5);
        executor.setThreadNamePrefix("AsyncExecutor-");
        executor.initialize();
        return executor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return (ex, method, params) -> {
            logger.error(method.toGenericString(), "异步调用出错：", ex);
        };
    }
}

package com.wdc.test.task;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyElasticJob implements SimpleJob {

    @Override
    public void execute(ShardingContext context) {
        switch (context.getShardingItem()) {
            case 0:
                // do something by sharding item 0
                log.info("item0");
                break;
            case 1:
                // do something by sharding item 1
                log.info("item1");
                break;
            case 2:
                // do something by sharding item 2
                log.info("item2");
                break;
            // case n: ...
        }
    }
}

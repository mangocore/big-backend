package com.mangocore.test.general;

import groovy.util.logging.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by notreami on 17/11/23.
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestThreadPoolTaskExecutor {

    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Test
    public void testThreadPoolTaskExecutor() {
        System.out.println(threadPoolTaskExecutor.getPoolSize());
    }
}

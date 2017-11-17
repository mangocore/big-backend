package com.mangocore.test.operation;

import com.mangocore.biz.service.SimpleService;
import groovy.util.logging.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by notreami on 17/9/10.
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestSimpleService {
    @Autowired
    private SimpleService simpleService;

    @Test
    public void testSimpleService(){
        System.out.println(simpleService.selectSysDate());
    }
}

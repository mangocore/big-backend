package com.mangocore.test;

import com.mangocore.api.AuthTestController;
import groovy.util.logging.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by notreami on 17/9/9.
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestAuthTestController {
    @Autowired
    private AuthTestController authTestController;

    @Value("${spring.profiles.active}")
    private String projectEnv;

    @Test
    public void sss(){
        System.out.println(projectEnv);
    }

}

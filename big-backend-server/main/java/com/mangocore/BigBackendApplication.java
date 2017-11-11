package com.mangocore;

import com.mangocore.common.util.IdWorker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.data.mongo.MongoRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.autoconfigure.websocket.WebSocketAutoConfiguration;
import org.springframework.boot.autoconfigure.websocket.WebSocketMessagingAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by notreami on 17/9/8.
 */
@SpringBootApplication(exclude = {WebSocketAutoConfiguration.class, WebSocketMessagingAutoConfiguration.class,
        RabbitAutoConfiguration.class,
        MongoAutoConfiguration.class, MongoDataAutoConfiguration.class,
        EmbeddedMongoAutoConfiguration.class, MongoRepositoriesAutoConfiguration.class})
@Slf4j
@EnableAsync
@EnableRetry
@EnableScheduling
@ServletComponentScan
public class BigBackendApplication {

    public static void main(String[] args) {
        IdWorker.getInstance(0, 0);//初始化随即生成器
        SpringApplication.run(BigBackendApplication.class, args);
    }
}

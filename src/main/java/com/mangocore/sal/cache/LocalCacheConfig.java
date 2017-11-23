package com.mangocore.sal.cache;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.google.common.collect.Lists;
import lombok.Getter;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 本地缓存
 * Created by notreami on 17/9/13.
 */
@Configuration
@EnableCaching
public class LocalCacheConfig {

    @Getter
    public enum Caches {
        test(5, 10, TimeUnit.SECONDS),
        auto(30, 1000, TimeUnit.MINUTES);

        private int ttl;        //过期时间
        private int maxSize;    //最大數量
        private TimeUnit unit;  //时间单位

        Caches(int ttl, int maxSize, TimeUnit unit) {
            this.ttl = ttl;
            this.maxSize = maxSize;
            this.unit = unit;
        }
    }

    /**
     * 创建基于Caffeine的Cache Manager
     *
     * @return
     */
    @Bean
    @Primary
    public CacheManager caffeineCacheManager() {
        List<CaffeineCache> caches = Lists.newArrayList();
        for (Caches c : Caches.values()) {
            caches.add(new CaffeineCache(c.name(),
                    Caffeine.newBuilder().recordStats()
                            .expireAfterWrite(c.getTtl(), c.getUnit())
                            .maximumSize(c.getMaxSize())
                            .build())
            );
        }

        SimpleCacheManager cacheManager = new SimpleCacheManager();
        cacheManager.setCaches(caches);
        return cacheManager;
    }

}

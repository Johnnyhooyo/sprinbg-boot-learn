package com.hyq.caffeine;

import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * @author dibulidohu
 * @classname CacheManagerConfig
 * @date 2019/5/2314:40
 * @description
 */
@Slf4j
@Configuration
public class CacheManagerConfig {

    private static final String CAFFEINE_SPEC = "initialCapacity=50,maximumSize=500,expireAfterWrite=5s,refreshAfterWrite=7s";
    private static final String CACHE_NAME_ONE = "outLimit";
    private static final String CACHE_NAME_TWO = "notOutLimit";
    private static ArrayList<String> list;
    static {
        list = new ArrayList<>();
        list.add(CACHE_NAME_ONE);
        list.add(CACHE_NAME_TWO);
    }

    @Bean
    public CacheManager cacheManagerWithCacheLoading() {
        Caffeine caffeine = Caffeine.newBuilder()
                .initialCapacity(10)
                .maximumSize(100)
                .expireAfterWrite(60, TimeUnit.SECONDS);
        CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager();
        caffeineCacheManager.setCaffeine(caffeine);
        caffeineCacheManager.setCacheNames(list);
        caffeineCacheManager.setAllowNullValues(true);
        return caffeineCacheManager;
    }

    @Bean(name = "caffeine")
    @Primary
    public CacheManager cacheManagerWithCaffeine(){
        log.info("This is cacheManagerWithCaffeine");
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        Caffeine caffeine = Caffeine.newBuilder()
                //cache的初始容量值
                .initialCapacity(100)
                //maximumSize用来控制cache的最大缓存数量，maximumSize和maximumWeight不可以同时使用，
                .maximumSize(1000);
        //控制最大权重
//                .maximumWeight(100);
//                .expireAfter();
        //使用refreshAfterWrite必须要设置cacheLoader
//                .refreshAfterWrite(5,TimeUnit.SECONDS);
        cacheManager.setCaffeine(caffeine);
//        cacheManager.setCacheLoader(cacheLoader);
        /* 如果这里初始化了缓存列表 则manager中的dynamic将被设为false 后面不能在get不存在的cache*/
        cacheManager.setCacheNames(list);
//        cacheManager.setAllowNullValues(false);
        return cacheManager;
    }

//    @Bean(name = "caffeineSpec")
//    public CacheManager cacheManagerWithCaffeineFromSpec(){
//        CaffeineSpec spec = CaffeineSpec.parse(CAFFEINE_SPEC);
//        Caffeine caffeine = Caffeine.from(spec);
//        //此方法等同于上面from(spec)
////        Caffeine caffeine = Caffeine.from(caffeineSpec);
//
//        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
//        cacheManager.setCaffeine(caffeine);
//        cacheManager.setCacheNames(list);
//        return cacheManager;
//    }
}

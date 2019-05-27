package com.hyq.caffeine;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.CacheLoader;
import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.cache.interceptor.NamedCacheResolver;
import org.springframework.cache.interceptor.SimpleCacheResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

import static java.util.Objects.requireNonNull;

/**
 * @author dibulidohu
 * @classname SolutionConfig
 * @date 2019/5/2711:09
 * @description
 */
@Slf4j
@Configuration
public class SolutionConfig {
    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private CacheLoader cacheLoader;

    @Bean("simpleCacheResolver")
    public CacheResolver simpleCacheResolver(){
        SimpleCacheResolver resolver = new SimpleCacheResolver(cacheManager);
        return resolver;
    }

    @Bean("namedCacheResolver")
    public CacheResolver namedCacheResolver(){
        NamedCacheResolver resolver = new NamedCacheResolver(cacheManager,"outLimit");
        return resolver;
    }

    @Bean("RemovalListener")
    public Cache removalListenerCache(){
        return Caffeine.newBuilder()
                .recordStats()
                .refreshAfterWrite(5, TimeUnit.SECONDS)
//                .removalListener((key, value, cause) ->  myRemovalListener(key, value, cause))
                .build(cacheLoader);
//                .build(key -> caffeineService.getCacheService(String.valueOf(key)));
    }

    @Bean
    public CacheLoader<Object,Object> cacheLoader(){
        CacheLoader<Object,Object> cacheLoader = new CacheLoader<Object, Object>() {
            @Override
            public Object load(Object key) throws Exception{
                System.out.println(System.currentTimeMillis()+" This is load key = " + key);
                return "new hyq";
            }

            @Override
            public Object reload(Object key, Object oldValue) throws Exception {
                System.out.println(System.currentTimeMillis()+" oldValue = " + oldValue);
                return this.load(key);
//                return oldValue+" a";
            }

            /**
             * 只要配置了这个方法，必定会先于reload执行
             * */
            @Override
            public CompletableFuture asyncReload(Object key, Object oldValue, Executor executor) {
                log.info("asyncReload key = {}, oldValue = {}",key,oldValue);
                requireNonNull(key);
                requireNonNull(executor);
                return CompletableFuture.supplyAsync(() -> {
                    try {
                        log.info("start to reload");
                        return reload(key, oldValue);
                    } catch (RuntimeException e) {
                        throw e;
                    } catch (Exception e) {
                        throw new CompletionException(e);
                    }
                }, executor);
            }

            @Override
            public CompletableFuture asyncLoad(Object key,  Executor executor) {
                System.out.println(System.currentTimeMillis()+" asyncLoadkey = " + key);
                return CompletableFuture.supplyAsync(() -> {
                    try {
                        return this.load(key);
                    } catch (RuntimeException var3) {
                        throw var3;
                    } catch (Exception var4) {
                        throw new CompletionException(var4);
                    }
                }, executor);
            }
        };
        return cacheLoader;
    }
}

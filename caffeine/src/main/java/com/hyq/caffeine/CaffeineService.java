package com.hyq.caffeine;

import com.github.benmanes.caffeine.cache.LoadingCache;
import com.github.benmanes.caffeine.cache.stats.CacheStats;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author dibulidohu
 * @classname Service
 * @date 2019/5/2711:03
 * @description
 */
@Slf4j
@Service
public class CaffeineService {

    @Autowired
    private CacheManager cacheManager;

    @Resource(name = "RemovalListener")
    private com.github.benmanes.caffeine.cache.Cache cache;

    public CacheStats stats() {
        CacheStats stats = cache.stats();
        log.info("averageLoadPenalty:{}, hitRate:{}, loadFailRate:{}, missRate:{}", stats.averageLoadPenalty(), stats.hitRate(), stats.loadFailureRate(), stats.missRate());
        //cleanUp()回收失效的数据
        cache.cleanUp();
        return null;
    }

    public void saveCache(String key) {
        cache.put(key, key);
        log.info("save key {} value {}", key, key);
    }

    public Object getCache(String key) {
        log.info("size = {}", cache.estimatedSize());
        LoadingCache loadingCache = (LoadingCache) cache;
        Object value = loadingCache.get(key);
        log.info("value = {}", value);
        return value;
    }

    public Object getCacheService(String key) {
        log.info("This is getCacheService");
        return key + " nihao ";
    }

    public Object getCacheService2(String key) {
        log.info("This is getCacheService2");
        return null;
    }

    /**
     * 如果cacheLoad生效这里的方法主体不会被执行
     */
    @Cacheable(cacheNames = "outLimit", key = "#name", cacheResolver = "simpleCacheResolver")
    public String addCaffeineServiceTest(String name) {
        String value = name + " nihao";
        log.info("addCaffeineServiceTest value = {}", value);
        return value;
    }

    /**
     * condition条件判断是否要走缓存，无法使用方法中出现的值（返回结果等）,条件为true放入缓存
     * <p>
     * unless是方法执行后生效，决定是否放入缓存,返回true的放缓存，与condition相反
     */
    @Cacheable(cacheNames = "outLimit", condition = "caffeineService.getCache(#name) != null", key = "#name", unless = "#age != null ")
    public String getCaffeineServiceTest(String name, Integer age) {
        String value = name + " nihao " + age;
        log.info("getCaffeineServiceTest value = {}", value);
        return value;
    }

    /**
     * CachePut修改key的value，会调用cache的put
     */
    @CachePut(cacheNames = "outLimit", cacheResolver = "namedCacheResolver")
    public String updateCaffeineServiceTest(String name) {
        String value = name + " nihao";
        log.info("updateCaffeineServiceTest value = {}", value);
        return null;
    }

    /**
     * CacheEvict删除key，会调用cache的evict
     */
    @CacheEvict(cacheNames = "outLimit", key = "#name")
    public String deleteCaffeineServiceTest(String name) {
        String value = name + " nihao";
        log.info("deleteCaffeineServiceTest value = {}", value);
        return value;
    }

    public void testCacheManager() {
        Cache cache = cacheManager.getCache("outLimit");
        log.info("cache = {}", cache.getNativeCache());
    }


    private void cacheResolver() {
        log.info("This is cacheResolver");
    }
}

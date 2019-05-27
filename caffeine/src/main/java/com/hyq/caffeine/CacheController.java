package com.hyq.caffeine;

import com.github.benmanes.caffeine.cache.stats.CacheStats;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.concurrent.Executor;

/**
 * @author dibulidohu
 * @classname CacheController
 * @date 2019/5/2316:57
 * @description
 */
@Slf4j
@RestController
@RequestMapping("/v1")
public class CacheController {

    @Autowired
    Facade facade;

    @Qualifier("caffeine")
    @Autowired
    CacheManager cacheManager;

    @Autowired
    Executor asyncServerExecutor;

    @GetMapping("/get")
    public String get() {
        return facade.getName(1);
    }

    @GetMapping("/put")
    public void put(@RequestParam Integer id, @RequestParam String name) {
        facade.putName(id, name);
    }

    @RequestMapping(value = "/find", method = {RequestMethod.GET})
    public void find() {
        log.info("find 1's name" );
        String name = facade.getName(1);
        log.info("找到了名字：{}", name);
    }

    @RequestMapping("/cache")
    public void cache() {
        Collection<String> cacheNames = cacheManager.getCacheNames();
        System.out.println(cacheNames);
        Cache user = cacheManager.getCache("outLimit");
        if (user != null) {
            user.putIfAbsent("1", "12");
            Cache.ValueWrapper valueWrapper = user.get("1");
            assert valueWrapper != null;
            Object o = valueWrapper.get();
            System.out.println(o);
        }
    }

    /**
     * caffeine 没有事务不支持下面的操作
     */
    @RequestMapping("/cache1")
    public void cache1() {
        Cache user = cacheManager.getCache("outLimit");

        for (int i = 0; i < 2; i++) {
            asyncServerExecutor.execute(() -> {
                Cache.ValueWrapper valueWrapper = user.get("1");
                Object o = valueWrapper.get();
                String o1 = (String) o;
                user.put("1", String.valueOf(Integer.valueOf(o1) + 1));
            });
        }
        Cache.ValueWrapper valueWrapper = user.get("1");
        assert valueWrapper != null;
        Object o = valueWrapper.get();
        System.out.println(o);
    }

    @Autowired
    private CaffeineService caffeineService;

    @GetMapping("/stats")
    public CacheStats stats(){
        return caffeineService.stats();
    }

    @GetMapping("/cache/save")
    public void saveIntoCache(@RequestParam String key){
        caffeineService.saveCache(key);
    }

    @GetMapping("/cache/get")
    public Object getIntoCache(@RequestParam String key){
        return caffeineService.getCache(key);
    }

    @PostMapping("/caffeine")
    public String CaffeineSaveTest(@RequestParam String name){
        log.info("CaffeineController name = " + name);
        return caffeineService.addCaffeineServiceTest(name);
    }

    @GetMapping("/caffeine")
    public String CaffeineGetTest(@RequestParam String name,@RequestParam Integer age){
        log.info("CaffeineController name = " + name);
        name = caffeineService.getCaffeineServiceTest(name, age);
        System.out.println("end name = " + name);
        return name;
    }

    @PutMapping("/caffeine")
    public String CaffeineUpdateTest(@RequestParam String name){
        log.info("CaffeineController name = " + name);
        return caffeineService.updateCaffeineServiceTest(name);
    }

    @DeleteMapping("/caffeine")
    public String CaffeineDeleteTest(@RequestParam String name){
        log.info("CaffeineController name = " + name);
        return caffeineService.deleteCaffeineServiceTest(name);
    }

    @GetMapping("manager")
    public void CacheManagerTest(){
        caffeineService.testCacheManager();
    }
}

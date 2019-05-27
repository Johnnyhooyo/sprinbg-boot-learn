package com.hyq.caffeine;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * @author dibulidohu
 * @classname Facade
 * @date 2019/5/2316:54
 * @description
 */
@Slf4j
@Component
public class Facade {

    private static HashMap<Integer, String> user = new HashMap<>();
    static {
        user.put(1, "hyq");
        user.put(2, "yqs");
    }

    @Cacheable(value = "user", key = "id", sync = false)
    public String getName(Integer id) {
        log.info("我是模拟的数据库");
        return user.get(id);
    }

    @Cacheable(value = "user", key = "id", sync = false)
    public void putName(Integer id, String name) {
        log.info("我是模拟的数据库");
        user.put(id, name);
    }
}

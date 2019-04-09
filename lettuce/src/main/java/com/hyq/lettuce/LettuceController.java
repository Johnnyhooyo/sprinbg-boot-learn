package com.hyq.lettuce;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;

/**
 * @author dibulidohu
 * @classname LettuceController
 * @date 2019/4/915:53
 * @description
 */
@Slf4j
@RestController
public class LettuceController {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private RedisTemplate<String, Serializable> redisTemplate;

    @RequestMapping("/v1/lettuce")
    @ResponseBody
    public void test(HttpServletRequest request, HttpServletResponse response) {
        // TODO 测试线程安全
//        ExecutorService executorService = Executors.newFixedThreadPool(1000);
//        IntStream.range(0, 1000).forEach(i ->
//                executorService.execute(() -> stringRedisTemplate.opsForValue().increment("kk", 1))
//        );
        stringRedisTemplate.opsForValue().set("k1", "v1");
        final String k1 = stringRedisTemplate.opsForValue().get("k1");
        log.info("[字符缓存结果] - [{}]", k1);
        // TODO 以下只演示整合，具体Redis命令可以参考官方文档，Spring Data Redis 只是改了个名字而已，Redis支持的命令它都支持
        String key = "battcn:user:1";
        redisTemplate.opsForValue().set(key, new User(1L, "u1", "pa"));
        // TODO 对应 String（字符串）
        final User user = (User) redisTemplate.opsForValue().get(key);
        log.info("[对象缓存结果] - [{}]", user);
    }
}


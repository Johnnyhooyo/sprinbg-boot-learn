package com.hyq.jedis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

import java.util.HashSet;
import java.util.Set;

/**
 * @author dibulidohu
 * @classname JedisController
 * @date 2019/4/1013:51
 * @description
 */
@Slf4j
@RestController
public class JedisController {


    @ResponseBody
    @RequestMapping("/v1/jedis")
    public void test() {
    }
}

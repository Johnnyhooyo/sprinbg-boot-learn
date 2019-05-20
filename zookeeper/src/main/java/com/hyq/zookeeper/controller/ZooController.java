package com.hyq.zookeeper.controller;

import com.hyq.zookeeper.core.service.ZooService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dibulidohu
 * @classname ZooController
 * @date 2019/5/2015:10
 * @description
 */
@Slf4j
@RestController
@RequestMapping("/zoo")
public class ZooController {


    @ZooService(serviceName = "HYQ.ZOO.HELLO", serviceDesc = "哈喽世界")
    @RequestMapping("/hello")
    public void hello(){
        log.info("service one have a request");
    }
}

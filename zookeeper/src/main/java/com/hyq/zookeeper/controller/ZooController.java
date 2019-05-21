package com.hyq.zookeeper.controller;

import com.hyq.zookeeper.core.service.CommenCllient;
import com.hyq.zookeeper.core.service.ServiceEnum;
import com.hyq.zookeeper.core.service.ZooService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

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
    @RequestMapping(value = "/hello", method = {RequestMethod.POST})
    public String hello(){
        log.info("service one have a request");
        return "heihei";
    }

    @ZooService(serviceName = "HYQ.CLIENT.REQUEST", serviceDesc = "请求示例")
    @RequestMapping(value = "/request", method = {RequestMethod.POST})
    public void  request(){
        log.info("have a request");
        try {
            String post = CommenCllient.post(ServiceEnum.DEMO_SERVICE, null);
            log.info("get response: {}", post);
        } catch (IOException e) {
           log.error("zooService request error:{}", e);
        }
    }
}

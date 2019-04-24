package com.hyq.rabbitmqconsumer.simple;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dibulidohu
 * @classname Test
 * @date 2019/4/1810:28
 * @description
 */
@RestController
public class Test {

    @RequestMapping("/test")
    public void sk() {
        System.out.println("00000000000000000000000000000000000000");
    }
}

package com.hyq.chatroom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author：huyuanqiang
 * @time: 2021-01-21 20:47
 * @description: 启动类
 **/
@SpringBootApplication
public class ChatRoomServer {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(ChatRoomServer.class);
    }
}

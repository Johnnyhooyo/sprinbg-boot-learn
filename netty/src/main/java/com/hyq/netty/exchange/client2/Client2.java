package com.hyq.netty.exchange.client2;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author dibulidohu
 * @classname Client1
 * @date 2019/4/2416:32
 * @description
 */
public class Client2 {
    public static void main(String[] args) throws Exception{
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();

        try{
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class)
                    .handler(new Client2Initializer());

            Channel channel = bootstrap.connect("localhost",8899).sync().channel();

            //标准输入
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

            //利用死循环，不断读取客户端在控制台上的输入内容
            for (;;){
                channel.writeAndFlush(bufferedReader.readLine() +"\r\n");
            }

        }finally {
            eventLoopGroup.shutdownGracefully();
        }
    }
}

package com.hyq.netty.restful;

import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.ssl.SslContext;

/**
 * @author dibulidohu
 * @classname ChannelInitializer
 * @date 2019/4/2411:41
 * @description
 */
public class ChannelInitializer extends io.netty.channel.ChannelInitializer<SocketChannel> {

    private final SslContext sslCtx;

    public ChannelInitializer(SslContext sslCtx) {
        this.sslCtx = sslCtx;
    }

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline p = socketChannel.pipeline();
        if (sslCtx != null) {
            p.addLast(sslCtx.newHandler(socketChannel.alloc()));
        }
        p.addLast(new HttpServerCodec());/*HTTP 服务的解码器*/
        p.addLast(new HttpObjectAggregator(2048));/*HTTP 消息的合并处理*/
        p.addLast(new HealthServerHandler()); /*自己写的服务器逻辑处理*/
    }
}

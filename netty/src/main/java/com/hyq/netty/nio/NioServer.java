package com.hyq.netty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

/**
 * @ClassName NioServer
 * @Author dibulidohu
 * @Date 2019/8/1 15:32
 * @Description
 */
public class NioServer {
    private Selector selector;

    public void initServer(int port) throws IOException {
        //通过加锁 保证了selector的唯一性  同步   configureBlocking(false); 非阻塞
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(new InetSocketAddress(port), 1024);
        this.selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        //OP_ACCEPT只能serverSocket用 其余三个是SocketChannel用
    }

    public void listen() throws IOException {
        System.out.println("服务端启动成功！");
        // 轮询访问selector
        while (true) {
            // 当注册的事件到达时，方法返回；否则,该方法会一直阻塞
            selector.select();
            // 获得selector中选中的项的迭代器，选中的项为注册的事件
            Iterator<SelectionKey> ite = this.selector.selectedKeys().iterator();
            while (ite.hasNext()) {
                SelectionKey key = ite.next();
                // 删除已选的key,以防重复处理
                ite.remove();

                // 客户端请求连接事件
                if (key.isAcceptable()) {
                    ServerSocketChannel server = (ServerSocketChannel) key.channel();
                    // 获得和客户端连接的通道
                    SocketChannel channel = server.accept();
                    // 设置成非阻塞
                    channel.configureBlocking(false);

                    // 在这里可以给客户端发送信息哦
                    channel.write(ByteBuffer.wrap("欢迎连接宇宙中心，请开始你的表演"
                            .getBytes(StandardCharsets.UTF_8)));
                    // 在和客户端连接成功之后，为了可以接收到客户端的信息，需要给通道设置读的权限。
                    channel.register(this.selector, SelectionKey.OP_READ);

                } else if (key.isReadable()) {
                    // 获得了可读的事件
                    read(key);
                }

            }

        }
    }

    public void read(SelectionKey key) throws IOException {
        // 服务器可读取消息:得到事件发生的Socket通道
        SocketChannel channel = (SocketChannel) key.channel();
        // 创建读取的缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(512);
        channel.read(buffer);
        byte[] data = buffer.array();
        String msg = new String(data).trim();
        System.out.println("服务端收到信息：" + msg);
        ByteBuffer outBuffer = ByteBuffer.wrap(msg.getBytes(StandardCharsets.UTF_8));
        // 将消息回送给客户端
        channel.write(outBuffer);
    }

    public static void main(String[] args) throws IOException {
        NioServer server = new NioServer();
        server.initServer(8000);
        server.listen();
    }
}

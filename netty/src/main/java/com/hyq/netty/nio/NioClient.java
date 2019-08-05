package com.hyq.netty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * @ClassName NioClient
 * @Author dibulidohu
 * @Date 2019/8/2 11:39
 * @Description
 */
public class NioClient implements Runnable{

    private Selector selector;
    private SocketChannel socketChannel;

    public NioClient() {
        try {
            this.selector = Selector.open();
            this.socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
            if (socketChannel.connect(new InetSocketAddress(8000))) {
                socketChannel.register(selector, SelectionKey.OP_READ);
            } else {
                socketChannel.register(selector, SelectionKey.OP_CONNECT);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void write() throws IOException {
        byte[] bytes = "hello im coming".getBytes();
        ByteBuffer byteBuffer = ByteBuffer.allocate(bytes.length);
        byteBuffer.put(bytes);
        byteBuffer.flip();
        socketChannel.write(byteBuffer);
        if (!byteBuffer.hasRemaining()) {
            System.out.println("send complete!");
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                selector.select();
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    SelectionKey next = iterator.next();
                    SocketChannel channel = (SocketChannel) next.channel();
                    iterator.remove();
                    if (next.isConnectable()) {
                        if (channel.finishConnect()) {
                            write();
                        }
                        socketChannel.register(selector, SelectionKey.OP_READ);
                    }
                    if (next.isReadable()) {
                        // 创建读取的缓冲区
                        ByteBuffer buffer = ByteBuffer.allocate(512);

                        channel.read(buffer);
                        byte[] data = buffer.array();
                        String msg = new String(data).trim();
                        System.out.println("服务端返回信息：" + msg);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Thread(new NioClient(),"001").start();
    }


}

package com.hyq.netty.io;

import java.awt.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @ClassName IoSystem
 * @Author dibulidohu
 * @Date 2019/8/1 10:10
 * @Description
 */
public class IoSystem {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9999);
        System.out.println("server start");
        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("some ome connect");
            InputStream inputStream = socket.getInputStream();
            byte[] buffer = new byte[1024];
            while (true) {
                int i = inputStream.read(buffer);
                System.out.println(i);
                if (i == -1) {
                    socket.close();
                    System.out.println("someone out");
                    break;
                } else  {
                    String data = new String(buffer, 0, i, "GBK");
                    System.out.println(data);
                    System.out.println("read over");
                    PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
                    writer.print("copy that");
                    writer.flush();

                }
            }
        }
    }


}

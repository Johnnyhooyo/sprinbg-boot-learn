package com.hyq.netty.io;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @ClassName IoClient
 * @Author dibulidohu
 * @Date 2019/8/1 13:33
 * @Description
 */
public class IoClient {
    public static void main(String[] args) throws IOException, InterruptedException {
        Socket socket = new Socket("127.0.0.1", 9999);
        OutputStream outputStream = socket.getOutputStream();
        InputStream inputStream = socket.getInputStream();
        PrintWriter printWriter = new PrintWriter(outputStream);
        byte[] buffer = new byte[1024];
        int count = 0;
        while (true) {
            Thread.sleep(100);

            printWriter.print("klsjdfsfiusifuailsufilsufliasufli" + count++);
            printWriter.flush();
            int i = inputStream.read(buffer);
            if (i!=-1) {
                System.out.println(new String(buffer, 0, i, "GBK"));
            }
            if(count == 10) {
                socket.shutdownOutput();
                break;
            }
        }
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
//        String line;
//        System.out.println(2222);
//        while ((line = bufferedReader.readLine()) != null) {
//            System.out.println(line);
//        }
    }
}

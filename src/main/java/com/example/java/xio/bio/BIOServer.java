package com.example.java.xio.bio;

/**
 * @author LingengMa
 * @date 2025/09/28 15:17
 * @Description:
 */


import java.io.*;
import java.net.*;

public class BIOServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8888);
        System.out.println("BIO Server started...");

        while (true) {
            Socket socket = serverSocket.accept(); // 阻塞等待客户端连接
            new Thread(() -> {
                try {
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String msg;
                    while ((msg = in.readLine()) != null) {  // 阻塞读取
                        System.out.println("Received by BIO server: " + msg);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}

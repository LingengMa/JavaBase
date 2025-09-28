package com.example.java.xio.aio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.Future;

/**
 * @author LingengMa
 * @date 2025/09/28 15:26
 * @Description:
 */



public class AIOClient {
    public static void main(String[] args) throws Exception {
        AsynchronousSocketChannel clientChannel = AsynchronousSocketChannel.open();

        Future<Void> connectFuture = clientChannel.connect(new InetSocketAddress("localhost", 8888));
        connectFuture.get(); // 等待连接完成

        String message = "Hello from AIO Client!";
        ByteBuffer buffer = ByteBuffer.wrap(message.getBytes());

        // 发送数据
        clientChannel.write(buffer).get();

        System.out.println("Sent message: " + message);

        // 可以加一个 read，实现双工通信
    }
}

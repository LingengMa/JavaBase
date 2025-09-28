package com.example.java.xio.aio;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * @author LingengMa
 * @date 2025/09/28 15:26
 * @Description:
 */


public class AIOServer {
    public static void main(String[] args) throws IOException, InterruptedException {
        // 1. 创建异步 Server Socket Channel
        AsynchronousServerSocketChannel serverChannel = AsynchronousServerSocketChannel.open();
        serverChannel.bind(new InetSocketAddress("localhost", 8888));

        System.out.println("AIO Server is listening on port 8888...");

        // 2. 开始异步 accept
        serverChannel.accept(null, new CompletionHandler<AsynchronousSocketChannel, Void>() {
            @Override
            public void completed(AsynchronousSocketChannel clientChannel, Void attachment) {
                // 接收下一个连接
                serverChannel.accept(null, this);

                try {
                    System.out.println("Connected client: " + clientChannel.getRemoteAddress());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                // 处理该客户端
                readData(clientChannel);
            }

            @Override
            public void failed(Throwable exc, Void attachment) {
                exc.printStackTrace();
            }
        });

        // 主线程不退出
        Thread.sleep(Long.MAX_VALUE);
    }

    private static void readData(AsynchronousSocketChannel clientChannel) {
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        clientChannel.read(buffer, buffer, new CompletionHandler<Integer, ByteBuffer>() {
            @Override
            public void completed(Integer result, ByteBuffer attachment) {
                if (result > 0) {
                    attachment.flip(); // 准备读
                    byte[] data = new byte[attachment.remaining()];
                    attachment.get(data);
                    System.out.println("Received: " + new String(data));
                }

                // 继续读取
                readData(clientChannel);
            }

            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {
                exc.printStackTrace();
                try {
                    System.out.println("Client disconnected: " + clientChannel.getRemoteAddress());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

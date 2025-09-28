package com.example.java.xio.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author LingengMa
 * @date 2025/09/28 15:21
 * @Description:
 */


public class client {
    public static void main(String[] args) throws IOException {

        try (SocketChannel clientChannel = SocketChannel.open()) {
            clientChannel.connect(new InetSocketAddress("localhost", 8888));
            ByteBuffer buffer = ByteBuffer.wrap("Hello from NIO client".getBytes());
            clientChannel.write(buffer);
        }

    }
}

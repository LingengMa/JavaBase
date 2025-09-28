package com.example.java.xio.bio;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author LingengMa
 * @date 2025/09/28 15:18
 * @Description:
 */


public class client {
    public static void main(String[] args) throws IOException {
        try(Socket socket = new Socket("localhost", 8888)) {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println("Hello from BIO client");
        }
    }
}

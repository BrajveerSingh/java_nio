package com.study.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class ChannelClient {
    public static void main(String[] args) throws IOException {
        final var inetSocketAddress = new InetSocketAddress("127.0.0.1", 12345);
        final var socketChannel = SocketChannel.open(inetSocketAddress);
        final var socket = socketChannel.socket();
        CharBuffer charBuffer = CharBuffer.allocate(1024);
        charBuffer.put("This is the test data...");
        charBuffer.flip();
        final var byteBuffer = StandardCharsets.UTF_8.encode(charBuffer);
        socketChannel.write(byteBuffer);
        socketChannel.close();
    }
}

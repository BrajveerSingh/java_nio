package com.study.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.Set;

public class SelectorsWithChannels {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        final var serverSocket = serverSocketChannel.socket();
        serverSocket.bind(new InetSocketAddress(12345));

        final var selector = Selector.open();

        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while(true){
            System.out.println("Waiting for events...");
            final var select = selector.select();
            System.out.println("Event=" + select);
            final var selectionKeys = selector.selectedKeys();
            for(var key : selectionKeys){
                if((key.readyOps() & SelectionKey.OP_ACCEPT) == SelectionKey.OP_ACCEPT){
                    //Accepted
                    ServerSocketChannel channel = (ServerSocketChannel) key.channel();
                    final var socketChannel = channel.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ);
                    selectionKeys.remove(key);
                } else if ((key.readyOps() & SelectionKey.OP_READ) == SelectionKey.OP_READ) {
                    System.out.println("Reading from connection...");
                    SocketChannel channel = (SocketChannel) key.channel();
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    channel.read(byteBuffer);
                    byteBuffer.flip();
                    CharBuffer charBuffer = StandardCharsets.UTF_8.decode(byteBuffer);
                    System.out.println(new String(charBuffer.array()));
                    byteBuffer.clear();
                    selectionKeys.remove(key);
                    key.cancel();
                    channel.close();
                }
            }
        }
    }
}

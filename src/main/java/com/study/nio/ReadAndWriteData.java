package com.study.nio;

import java.io.IOException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class ReadAndWriteData {
    public static void main(String[] args) throws IOException {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.putInt(12);
        byteBuffer.putInt(13);
//        System.out.println("position = " + byteBuffer.position());
//        System.out.println("limit = " + byteBuffer.limit());

        byteBuffer.flip();

//        System.out.println("position = " + byteBuffer.position());
//        System.out.println("limit = " + byteBuffer.limit());

        final var path = Paths.get("C:\\code\\Algorithms_Part_I\\src\\Files\\demo.bin");
        FileChannel fileChannel =
                FileChannel.open(path, StandardOpenOption.CREATE, StandardOpenOption.WRITE);
        fileChannel.write(byteBuffer);

        fileChannel.close();

        FileChannel readChannel = FileChannel.open(path, StandardOpenOption.READ);
        byteBuffer.clear();
        readChannel.read(byteBuffer);
        readChannel.close();

        byteBuffer.flip();

        try {
            while (true) {
//                System.out.println("contents = " + byteBuffer.get());
                System.out.println("contents = " + byteBuffer.getInt());
            }
        }catch (BufferUnderflowException ignored){

        }
    }
}

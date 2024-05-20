package com.study.nio;

import java.io.IOException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class CharsetAndCharBuffer {
    public static void main(String[] args) {
        String str = "यस्य कृत्यं न जानन्ति मन्त्रं वा मन्त्रितं परे। कृतमेवास्य जानन्ति स वै पण्डित उच्यते";
        CharBuffer charBuffer = CharBuffer.allocate(1024);

        charBuffer.put(str);

        charBuffer.flip();

        final var utf8 = StandardCharsets.UTF_8;
        final var byteBuffer = utf8.encode(charBuffer);
        final var path = Paths.get("C:/code/Algorithms_Part_I/src/Files/shloka.txt");
        try(FileChannel fileChannel = FileChannel.open(path, StandardOpenOption.CREATE, StandardOpenOption.WRITE)){
            fileChannel.write(byteBuffer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try(FileChannel fileChannel = FileChannel.open(path, StandardOpenOption.READ)){
            byteBuffer.clear();
            fileChannel.read(byteBuffer);
            byteBuffer.flip();

            final var decodedCharBuffer = utf8.decode(byteBuffer);

            try {
                while (true) {
                    System.out.print(decodedCharBuffer.get());
                }
            }catch (BufferUnderflowException ignored){

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}

package com.study.nio;

import java.io.IOException;
import java.net.URI;
import java.nio.file.FileStore;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.spi.FileSystemProvider;

public class FileSystemNio {
    public static void main(String[] args) throws IOException {
        final var fileSystemProviders = FileSystemProvider.installedProviders();
        fileSystemProviders.forEach(System.out::println);
        final var uri = URI.create("file:///");
//        final var defaultFileSystem = fileSystemProviders.get(0).getFileSystem(uri);
        System.out.println("uri=" + uri);
        final var defaultFileSystem = FileSystems.getDefault();
        System.out.println("defaultFileSystem=" + defaultFileSystem);
        final var rootDirectories = defaultFileSystem.getRootDirectories();
        System.out.println("Root Directories:");
        for (Path path : rootDirectories) {
            System.out.println("path=" + path);
        }

        final var fileStores = defaultFileSystem.getFileStores();
        System.out.println("File stores:");
        for (FileStore fileStore : fileStores) {
            System.out.println("name=" + fileStore.name());
            System.out.println("type=" + fileStore.type());
            System.out.println("total space(bytes)=" + fileStore.getTotalSpace());
        }

        final var fileSystemProvider = fileSystemProviders.get(0);
        fileSystemProvider.createDirectory(Paths.get(URI.create("file:///Users/shared/tmp")));
    }
}

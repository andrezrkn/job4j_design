package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class Dir {
    public static void main(String[] args) throws IOException {
        File file = new File("c:\\projects");
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }
        Files.walkFileTree(file.toPath(), new SimpleFileVisitor<>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                File f = new File(file.toAbsolutePath().toString());
                System.out.printf(file.getFileName() + " %d", f.length());
                System.out.println("");
                return super.visitFile(file, attrs);
            }
        });
    }
}

package ru.job4j.io.duplicates;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        HashMap<FileProperty, Path> fileAndPath = new HashMap<>();
        Files.walkFileTree(Path.of("./"), new SimpleFileVisitor<>() {
            @Override
            public FileVisitResult visitFile(Path file,
                                             BasicFileAttributes attrs)
                    throws IOException {
                File f = new File(file.toAbsolutePath().toString());
                FileProperty property = new FileProperty(f.length(), f.getName());
                if (!fileAndPath.isEmpty()) {
                    if (fileAndPath.containsKey(property)) {
                        System.out.printf("A copy of the file exists."
                                + " It's location: %s%n%s",
                                f, fileAndPath.get(property).toAbsolutePath());
                    } else {
                        fileAndPath.put(property, file.toAbsolutePath());
                    }
                } else {
                    fileAndPath.put(property, file.toAbsolutePath());
                }
                return super.visitFile(file, attrs);
            }
        });
    }
}
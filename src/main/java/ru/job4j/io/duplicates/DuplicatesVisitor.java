package ru.job4j.io.duplicates;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    HashMap<FileProperty, Path> fileAndPath = new HashMap<>();

    private static void print(File file, Path path) {
        System.out.printf("%s%n%s%n", file, path);
    }

    @Override
    public FileVisitResult visitFile(Path file,
                                     BasicFileAttributes attrs)
            throws IOException {
        File f = new File(file.toAbsolutePath().toString());
        FileProperty property = new FileProperty(f.length(), f.getName());
        if (!fileAndPath.isEmpty()) {
            if (fileAndPath.containsKey(property)) {
                print(f, fileAndPath.get(property).toAbsolutePath());
            } else {
                fileAndPath.put(property, file.toAbsolutePath());
            }
        } else {
            fileAndPath.put(property, file.toAbsolutePath());
        }
        return super.visitFile(file, attrs);
    }
}

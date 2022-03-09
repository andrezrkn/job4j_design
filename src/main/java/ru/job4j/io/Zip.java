package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path el : sources) {
                zip.putNextEntry(new ZipEntry(el.toString()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(el.toString()))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void validate(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("the program uses 3 arguments, enter them before running");
        }
        if (args.length != 3) {
            throw new IllegalArgumentException("the number of arguments is not equal to three");
        }
    }

    private void validatePath(String arg) {
        File file = new File(arg);
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }
    }

    public void logic(Zip zip, String[] args) throws IOException {
        zip.validate(args);
        ArgsName argsName = ArgsName.of(args);
        zip.validatePath(argsName.get("d"));
        List<Path> rsl = Search.search(new File(argsName.get("d")).toPath(),
                p -> !p.toFile().getName().endsWith(argsName.get("e")));
        zip.packFiles(rsl, new File(argsName.get("o")));
    }

    public static void main(String[] args) throws IOException {
        Zip zip = new Zip();
        zip.logic(zip, args);
    }
}
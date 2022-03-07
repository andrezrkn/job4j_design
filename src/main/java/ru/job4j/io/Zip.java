package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<File> sources, File target) {
        Zip zip = new Zip();
        for (File el : sources) {
            zip.packSingleFile(el, target);
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
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
                p -> p.toFile().getName().endsWith(argsName.get("e")));
        List<File> list = new ArrayList<>();
        for (Path el : rsl) {
            list.add(el.toFile());
        }
        zip.packFiles(list, new File(argsName.get("o")));
    }

    public static void main(String[] args) throws IOException {
        Zip zip = new Zip();
        zip.packSingleFile(
                new File("./pom.xml"),
                new File("./pom.zip")
        );
        zip.logic(zip, args);
    }
}
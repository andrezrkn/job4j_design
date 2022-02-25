package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException("key don't exist");
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        for (String el : args) {
            String[] cell = {"", ""};
            if (el.startsWith("-") && el.contains("=")) {
                cell = el.split("=", 2);
                cell[0] = cell[0].substring(1);
            }
            if (el.startsWith("=") || cell[1].equals("")) {
                throw new IllegalArgumentException("key or value don't exist");
            } else {
                cell[1] = cell[1].trim();
            }
            values.put(cell[0], cell[1]);
        }
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}

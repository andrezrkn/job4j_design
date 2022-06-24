package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Config {

    private final String path;

    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    public Map<String, String> getValues() {
        return values;
    }

    private String[] validate(String element) {
        String[] cell = {"", ""};
        if (element.contains("=")) {
            cell = element.split("=", 2);
        }
        if (element.startsWith("=") || element.startsWith("#")
                || cell[1].equals("")) {
            cell[0] = null;
        } else {
            cell[1] = cell[1].trim();
        }
        return cell;
    }

    public void load() {
        List<String> mass = new ArrayList<>();
        String[] value;
        try (BufferedReader read = new BufferedReader(new FileReader(this.path
        ))) {
            read.lines().filter(el -> el.contains("=")).forEach(mass::add);
            for (String element : mass) {
                value = validate(element);
                if (value[0] != null) {
                    values.put(value[0], value[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        String val = values.get(key);
        if (val == null) {
            throw new IllegalArgumentException("key doesn't exist");
        }
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path
        ))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        Config config = new Config("app.properties");
        System.out.println(config);
        config.load();
    }

}

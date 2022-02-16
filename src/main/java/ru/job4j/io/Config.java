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

    private String[] validate(String element) {
        String[] cell = new String[2];
        cell[0] = "";
        cell[1] = "";
        char[] elementBuffer = element.toCharArray();
        int elLength = elementBuffer.length;
        if (elementBuffer[0] == '=' || elementBuffer[elLength - 1] == '=') {
            cell[0] = null;
        } else {
            int equalIndex = 0, endValue = 0;
            for (int i = 0; i < elLength; i++) {
                if (elementBuffer[i] == '=') {
                    equalIndex = i;
                    break;
                } else {
                    cell[0] += elementBuffer[i];
                }
            }
            int startValue = equalIndex + 1;
            for (int i = equalIndex + 1; i < elLength; i++) {
                if (elementBuffer[i] != ' ') {
                    startValue = i;
                    break;
                }
            }
            for (int i = elLength - 1; i > equalIndex + 1; i--) {
                if (elementBuffer[i] != ' ') {
                    endValue = i;
                    break;
                }
            }
            for (int i = startValue; i < endValue + 1; i++) {
                cell[1] += elementBuffer[i];
            }
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

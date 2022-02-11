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

    public void load() {
        List<String> mass = new ArrayList<>();
        int equalIndex = 0;
        String key, value;
        char[] strBuff = null;
        try (BufferedReader read = new BufferedReader(new FileReader(this.path
        ))) {
            read.lines().filter(el -> el.contains("=")).forEach(mass::add);
            for (String el : mass) {
                strBuff = el.toCharArray();
                int newLengthStrBuff = strBuff.length, spaceCount = 0;
                for (int i = strBuff.length - 1; i > 0; i--) {
                    if (strBuff[i] != ' ') {
                        break;
                    } else {
                        newLengthStrBuff--;
                    }
                }
                for (int i = 0; i < strBuff.length; i++) {
                    if (strBuff[i] == ' ') {
                        spaceCount++;
                    } else {
                        break;
                    }
                }
                char[] trueStrBuff = new char[newLengthStrBuff - spaceCount];
                if (spaceCount != 0 || strBuff.length != newLengthStrBuff) {
                    for (int i = spaceCount; i < newLengthStrBuff; i++) {
                        trueStrBuff[i - spaceCount] = strBuff[i];
                    }
                } else {
                    trueStrBuff = strBuff;
                }
                String fin = new String(trueStrBuff);
                equalIndex = fin.indexOf("=");
                key = fin.substring(0, equalIndex);
                if (key.isEmpty()) {
                    throw new IllegalArgumentException("key is empty");
                }
                value = fin.substring(equalIndex + 1);
                values.put(key, value);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
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

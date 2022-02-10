package ru.job4j.io;

import java.io.*;
import java.util.*;

public class LogFilter {
    public List<String> filter(String file) {
        List<String> list = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(
                new FileReader(file))) {
            for (String line = in.readLine(); line != null;
                 line = in.readLine()) {
                if (line.contains(" 404 ")) {
                    list.add(line);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void save(List<String> log, String file) {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(file)
                ))) {
            for (String el : log) {
                out.printf("%s%n", el);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter();
        List<String> log = logFilter.filter("log.txt");
        for (String el : log) {
            System.out.println(el);
        }
        save(log, "404.txt");
    }
}

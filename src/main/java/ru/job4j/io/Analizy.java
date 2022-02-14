package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Analizy {
    private List<String> logic(List<String> data) {
        List<String> result = new ArrayList<>();
        boolean flag = false, endOfPeriod = false;
        String period = "";
        for (String el : data) {
            if (!flag && (el.contains("400 ") || el.contains("500 "))) {
                flag = true;
                period += el.substring(4) + ";";
            } else if (flag && (el.contains("200 ") || el.contains("300 "))) {
                flag = false;
                endOfPeriod = true;
                period += el.substring(4);
            }
            if (endOfPeriod) {
                endOfPeriod = false;
                result.add(period);
                period = "";
            }
        }
        return result;
    }

    public void unavailable(String source, String target) {
        boolean flag = false;
        List<String> data = new ArrayList<>();
        List<String> result;

        try (BufferedReader in = new BufferedReader(
                new FileReader(source))) {
            for (String line = in.readLine(); line != null;
                 line = in.readLine()) {
                data.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        result = logic(data);

        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(target)
                ))) {
            for (String el : result) {
                out.println(el);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

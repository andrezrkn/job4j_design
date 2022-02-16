package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Analizy {
    private void save(String data, String target) {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(target)
                ))) {
                out.println(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void unavailable(String source, String target) {
        List<String> result = new ArrayList<>();

        try (BufferedReader in = new BufferedReader(new FileReader(source))) {
            boolean flag = false, endOfPeriod = false;
            StringBuilder period = new StringBuilder();
            for (String line = in.readLine(); line != null;
                 line = in.readLine()) {
                if (!flag && (line.contains("400 ") || line.contains("500 "))) {
                    flag = true;
                    period.append(line.substring(4)).append(";");
                } else if (flag && (line.contains("200 ") || line.contains("300 "))) {
                    flag = false;
                    endOfPeriod = true;
                    period.append(line.substring(4));
                }
                if (endOfPeriod) {
                    endOfPeriod = false;
                    save(period.toString(), target);
                    period = new StringBuilder();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analizy ob = new Analizy();
        try (PrintWriter out = new PrintWriter(new FileOutputStream("unavailable.csv"))) {
            out.println("200 10:56:01");
            out.println("500 10:57:01");
            out.println("400 10:58:01");
            out.println("200 10:59:01");
            out.println("500 11:01:02");
            out.println("200 11:02:02");
        } catch (Exception e) {
            e.printStackTrace();
        }
        ob.unavailable("unavailable.csv", "file.txt");
    }
}

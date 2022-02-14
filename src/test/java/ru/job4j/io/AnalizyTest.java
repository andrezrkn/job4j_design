package ru.job4j.io;

import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

public class AnalizyTest {

    @Test
    public void firstTestJob4j() {
        boolean result = true;
        List<String> rslList = List.of("10:57:01;10:59:01", "11:01:02;11:02:02");
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
        ob.unavailable("unavailable.csv", "time.csv");
        try (BufferedReader in = new BufferedReader(
                new FileReader("time.csv"))) {
            for (String line = in.readLine(); line != null;
                 line = in.readLine()) {
                if (!rslList.contains(line)) {
                    result = false;
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertTrue(result);
    }


    @Test
    public void secondTestJob4j() {
        boolean result = true;
        List<String> rslList = List.of("10:57:01;11:02:02");
        Analizy ob = new Analizy();
        try (PrintWriter out = new PrintWriter(new FileOutputStream("unavailable.csv"))) {
            out.println("200 10:56:01");
            out.println("500 10:57:01");
            out.println("400 10:58:01");
            out.println("500 10:59:01");
            out.println("400 11:01:02");
            out.println("200 11:02:02");
        } catch (Exception e) {
            e.printStackTrace();
        }
        ob.unavailable("unavailable.csv", "time.csv");
        try (BufferedReader in = new BufferedReader(
                new FileReader("time.csv"))) {
            for (String line = in.readLine(); line != null;
                 line = in.readLine()) {
                if (!rslList.contains(line)) {
                    result = false;
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertTrue(result);
    }
}
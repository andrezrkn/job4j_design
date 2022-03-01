package ru.job4j.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Random;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        List<String> phrases = readPhrases();
        List<String> log = new ArrayList<>();
        Scanner input = new Scanner(System.in);
        Random random = new Random();
        String in;
        boolean outFlag = true, stopFlag = true;
        while (outFlag) {
            in = input.nextLine();
            if (!OUT.equals(in)) {
                if (STOP.equals(in)) {
                    stopFlag = false;
                }
                if (CONTINUE.equals(in)) {
                    stopFlag = true;
                }
                log.add(in);
                if (stopFlag) {
                    String phrase = phrases.get(random.nextInt(phrases.size())
                    );
                    System.out.println(phrase);
                    log.add(phrase);
                }
            } else {
                log.add(in);
                outFlag = false;
            }
        }
        saveLog(log);
    }

    private List<String> readPhrases() {
        List<String> phrases = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(botAnswers, StandardCharsets.UTF_8))) {
            br.lines().forEach(phrases::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return phrases;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter pw = new PrintWriter(
                new FileWriter(path, StandardCharsets.UTF_8, true))) {
            for (String el : log) {
                pw.println(el);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void validate(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("the program uses 2 arguments,"
                    + " enter them before running. "
                    + "Example: C:/q/1.txt C:/r/2.js");
        }
        if (args.length != 2) {
            throw new IllegalArgumentException("the number of arguments is not equal to two");
        }
        if (!args[0].contains(".") || !args[1].contains(".")) {
            throw new IllegalArgumentException("enter the correct file names. "
                    + "Example: C:/job4j/name.txt");
        }
        File file = new File(args[0]);
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        file = new File(args[1]);
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }
    }

    public static void main(String[] args) {
        ConsoleChat.validate(args);
        ConsoleChat cc = new ConsoleChat(args[0], args[1]);
        cc.run();
    }
}

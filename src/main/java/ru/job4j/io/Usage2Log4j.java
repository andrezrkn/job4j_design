package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Usage2Log4j {

    private static final Logger LOG = LoggerFactory.getLogger(Usage2Log4j.class.getName());

    public static void main(String[] args) {
        byte a = 0;
        short b = 1;
        int c = 2;
        long d = 3;
        float e = 4;
        double f = 5;
        char j = '6';
        boolean h = true;
        LOG.debug("{}, {}, {}, {}, {}, {}, {}, {}", a, b, c, d, e, f, j, h);
    }
}
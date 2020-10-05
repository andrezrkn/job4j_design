package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row = 0;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        int falseColumn = 0;
        boolean rsl;
        int sum = 0;
        for (int[] datum : data) {
            sum += datum.length;
        }
        if (sum == 0) {
            rsl = false;
        } else  {
            while (data[column].length == 0) {
                column++;
                falseColumn++;
            }
            rsl = (row + 1) * (column - falseColumn + 1) <= sum + 1;
        }
        return rsl;
    }

    @Override
    public Integer next() {
        boolean flag = false;
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        if (row < data[column].length - 1) {
            row++;
            flag = true;
        } else {
            row = 0;
            column++;
        }
        return flag ? data[column][row - 1]
                : data[column - 1][data[column - 1].length - 1];
    }
}
//package ru.job4j.it;
//
//import java.util.Iterator;
//import java.util.NoSuchElementException;
//
//public class MatrixIt implements Iterator<Integer> {
//    private final int[][] data;
//    private int row = 0;
//    private int column = 0;
//
//    public MatrixIt(int[][] data) {
//        this.data = data;
//    }
//
//    @Override
//    public boolean hasNext() {
//        int column = this.column;
//        boolean rsl;
//        int sum = 0;
//        for (int[] datum : data) {
//            sum += datum.length;
//        }
//        if (sum == 0) {
//            rsl = false;
//        } else if (data[this.column].length == 0) {
//            rsl = true;
//        } else {
//            while (data[column].length == 0) {
//                column++;
//            }
//            rsl = (row + 1) * (column + 1) < sum;
//        }
//        return rsl;
//    }
//
//    @Override
//    public Integer next() {
//        boolean flag = false;
//        if (!hasNext()) {
//            throw new NoSuchElementException();
//        }
//        if (row < data[column].length - 1) {
//            row++;
//            flag = true;
//        } else {
//            row = 0;
//            do {
//                column++;
//            } while (data[column].length == 0);
//        }
//        return flag ? data[column][row - 1]
//                : data[column - 1][data[column - 1].length - 1];
//    }
//}
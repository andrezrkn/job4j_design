//package ru.job4j.it;
//import java.util.Iterator;
//import java.util.NoSuchElementException;
//
//public class EvenNumbersIterator implements Iterator<Integer> {
//    private final int[] data;
//    private int point = 0;
//
//    public EvenNumbersIterator(int[] data) {
//        this.data = data;
//    }
//
//    @Override
//    public boolean hasNext() {
//        boolean rsl = false;
//        for (int i = point + 1; i < data.length; i++) {
//            if (data[i] % 2 == 0) {
//                rsl = true;
//                break;
//            }
//        }
//        return rsl;
//    }
//
//    @Override
//    public Integer next() {
//        int space = 0;
//        if (!hasNext()) {
//            throw new NoSuchElementException();
//        }
//        if (data[point] % 2 != 0) {
//            point++;
//        }
//        for (int i = point + 1; i < data.length; i++) {
//            space++;
//            if (data[i] % 2 == 0) {
//                point = i;
//                break;
//            }
//        }
//        return data[point - space];
//    }
//}

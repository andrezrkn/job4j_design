package ru.job4j.it;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Balancer {
    public static void split(List<ArrayList<Integer>> nodes, Iterator<Integer> source) {
        for (int i = 0; i < nodes.size(); i++) {
            if (source.hasNext()) {
                nodes.get(i).add(source.next());
            }
            if (source.hasNext() && i + 1 == nodes.size()) {
                i = -1;
            }
        }
    }
}

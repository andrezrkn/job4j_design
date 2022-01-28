package ru.job4j.set;

import ru.job4j.list.SimpleArrayList;
import java.util.Iterator;

public class SimpleSet<T> implements Set<T> {

    private SimpleArrayList<T> set = new SimpleArrayList<>();

    @Override
    public boolean add(T value) {
        return false;
    }

    @Override
    public boolean contains(T value) {
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }
}

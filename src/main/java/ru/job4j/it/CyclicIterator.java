package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.List;

public class CyclicIterator<T> implements Iterator<T> {
    private List<T> data;

    private int index;

    public CyclicIterator(List<T> data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        if (index == data.size()) {
            index = 0;
        }
        return index < data.size();
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data.get(index++);
    }
}

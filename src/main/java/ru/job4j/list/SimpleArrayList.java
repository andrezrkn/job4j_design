package ru.job4j.list;

import java.util.*;

public class SimpleArrayList<T> implements List<T> {

    private T[] container;
    private int size;
    private int modCount;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
        size = 0;
    }

    private void increase(T value) {
        if (size == container.length) {
            if (container.length == 0) {
                container = (T[]) new Object[1];
            }
            container = Arrays.copyOf(container, container.length * 2);
        }
    }

    @Override
    public void add(T value) {
        modCount++;
        increase(value);
        container[size++] = value;
    }

    @Override
    public T set(int index, T newValue) {
        T el = get(index);
        container[index] = newValue;
        return el;
    }

    @Override
    public T remove(int index) {
        modCount++;
        T el = get(index);
        System.arraycopy(container, index + 1, container, index,
                size - index - 1);
        container[--size] = null;
        return el;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return container[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int point = 0;
            private int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return point < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[point++];
            }
        };
    }
}

package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;
    private int capacity = 8;
    private int count = 0;
    private int modCount = 0;
    private MapEntry<K, V>[] table = new MapEntry[capacity];

    private int hash(int hashCode) {
        return (hashCode ^ (hashCode >>> 16)) & (capacity - 1);
    }

    private int indexFor(int hash) {
        return 0;
    }

    private void expand() {
        capacity *= 2;
        MapEntry<K, V>[] bufTable = new MapEntry[capacity];
        for (MapEntry<K, V> el : table) {
            if (el != null) {
                bufTable[hash(el.key.hashCode())] = el;
            }
        }
        table = bufTable;
    }

    @Override
    public boolean put(K key, V value) {
        int hash; boolean rsl = false;
        if (count / (double) capacity >= LOAD_FACTOR) {
            expand();
        }
        hash = hash(key.hashCode());
        if (table[hash] == null) {
            table[hash] = new MapEntry<>(key, value);
            rsl = true;
            modCount++;
            count++;
        }
        return rsl;
    }

    @Override
    public V get(K key) {
        int hash = hash(key.hashCode());
        return count == 0 ? null : (table[hash].key.equals(key)
                ? table[hash].value : null);
    }

    @Override
    public boolean remove(K key) {
        boolean rsl = false;
        int hash = hash(key.hashCode());
        if (table[hash] != null && table[hash].key.equals(key)) {
            table[hash] = null;
            count--;
            modCount++;
            rsl = true;
        }
        return rsl;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<>() {
            private int point = 0;
            private int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                boolean rsl = false;
                while (point < capacity) {
                    if (table[point] != null) {
                        rsl = true;
                        break;
                    }
                    point++;
                }
                return rsl;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[point++].key;
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}

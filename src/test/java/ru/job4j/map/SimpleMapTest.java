package ru.job4j.map;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;

public class SimpleMapTest {
    @Test
    public void whenPut() {
        SimpleMap<Integer, Integer> map = new SimpleMap<>();
        assertTrue(map.put(1, 1));
        assertTrue(map.put(2, 2));
        assertTrue(map.put(3, 3));
        assertTrue(map.put(4, 4));
    }

    @Test
    public void whenPutAndExpand() {
        SimpleMap<Integer, Integer> map = new SimpleMap<>();
        assertTrue(map.put(1, 1));
        assertTrue(map.put(2, 2));
        assertTrue(map.put(3, 3));
        assertTrue(map.put(4, 4));
        assertTrue(map.put(5, 5));
        assertTrue(map.put(6, 6));
        assertTrue(map.put(7, 7));
        assertTrue(map.put(8, 8));
    }

    @Test
    public void whenRemoveAndGet() {
        SimpleMap<Integer, Integer> map = new SimpleMap<>();
        assertTrue(map.put(1, 1));
        assertTrue(map.remove(1));
        assertNull(map.get(1));
    }

    @Test
    public void whenPutAndGet() {
        SimpleMap<Integer, Integer> map = new SimpleMap<>();
        assertTrue(map.put(1, 1));
        assertTrue(map.put(2, 2));
        assertTrue(map.put(3, 3));
        assertThat(map.get(1), is(1));
        assertThat(map.get(3), is(3));
    }

    @Test
    public void whenPutAndHasNextTrueThenIterator() {
        SimpleMap<Integer, Integer> map = new SimpleMap<>();
        map.put(1, 1);
        assertTrue(map.iterator().hasNext());
    }

    @Test
    public void whenPutX2AndHasNextTrueThenIterator() {
        SimpleMap<Integer, Integer> map = new SimpleMap<>();
        map.put(1, 1);
        map.put(2, 2);
        assertTrue(map.iterator().hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void whenPutAndRemoveExpectException() {
        SimpleMap<Integer, Integer> map = new SimpleMap<>();
        map.put(1, 1);
        map.remove(1);
        map.iterator().next();
    }

    @Test
    public void whenPutX2AndNext() {
        SimpleMap<Integer, Integer> map = new SimpleMap<>();
        map.put(1, 1);
        map.put(2, 2);
        Iterator<Integer> iterator = map.iterator();
        assertThat(iterator.next(), is(1));
    }
}
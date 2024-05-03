package ru.job4j.map;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;
import java.util.Iterator;
import java.util.NoSuchElementException;

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
        assertEquals(map.get(1), 1);
        assertEquals(map.get(3), 3);
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

    @Test
    public void whenPutAndRemoveExpectException() {
        SimpleMap<Integer, Integer> map = new SimpleMap<>();
        map.put(1, 1);
        map.remove(1);
        assertThatThrownBy(() -> map.iterator().next())
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    public void whenPutX2AndNext() {
        SimpleMap<Integer, Integer> map = new SimpleMap<>();
        map.put(1, 1);
        map.put(2, 2);
        Iterator<Integer> iterator = map.iterator();
        assertEquals(iterator.next(), 1);
    }
}
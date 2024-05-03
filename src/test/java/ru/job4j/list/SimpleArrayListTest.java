package ru.job4j.list;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.stream.IntStream;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

public class SimpleArrayListTest {

    private List<Integer> list;

    @BeforeEach
    public void initData() {
        list = new SimpleArrayList<>(3);
        list.add(1);
        list.add(2);
        list.add(3);
    }

    @Test
    public void whenAddThenSizeIncrease() {
        assertEquals(3, list.size());
    }

    @Test
    public void whenAddAndGetByCorrectIndex() {
        assertEquals(Integer.valueOf(1), list.get(0));
    }

    @Test
    public void whenAddAndGetByIncorrectIndexThenGetException() {
        assertThatThrownBy(() -> list.get(5))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    public void whenRemoveThenGetValueAndSizeDecrease() {
        assertEquals(3, list.size());
        assertEquals(Integer.valueOf(2), list.remove(1));
        assertEquals(2, list.size());
    }

    @Test
    public void whenRemoveByIncorrectIndexThenGetException() {
        assertThatThrownBy(() -> list.remove(5))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    public void whenRemoveThenMustNotBeEmpty() {
        list.remove(1);
        assertEquals(Integer.valueOf(1), list.get(0));
        assertEquals(Integer.valueOf(3), list.get(1));
    }

    @Test
    public void whenAddNullThenMustBeSameBehavior() {
        list = new SimpleArrayList<>(3);
        list.add(null);
        list.add(null);
        assertEquals(2, list.size());
        assertNull(list.get(0));
        assertNull(list.get(1));
    }

    @Test
    public void whenSetThenGetOldValueAndSizeNotChanged() {
        assertEquals(Integer.valueOf(2), list.set(1, 22));
        assertEquals(3, list.size());
    }

    @Test
    public void whenSetByIncorrectIndexThenGetException() {
        assertThatThrownBy(() -> list.set(5, 22))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    public void whenGetIteratorFromEmptyListThenHasNextReturnFalse() {
        list = new SimpleArrayList<>(5);
        assertFalse(list.iterator().hasNext());
    }

    @Test
    public void whenGetIteratorFromEmptyListThenNextThrowException() {
        list = new SimpleArrayList<>(5);
        assertThatThrownBy(() -> list.iterator().next())
                .isInstanceOf(NoSuchElementException.class);
    }

   @Test
    public void whenGetIteratorTwiceThenStartAlwaysFromBeginning() {
        assertEquals(Integer.valueOf(1), list.iterator().next());
        assertEquals(Integer.valueOf(1), list.iterator().next());
    }

    @Test
    public void whenCheckIterator() {
        Iterator<Integer> iterator = list.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(Integer.valueOf(1), iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(Integer.valueOf(2), iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(Integer.valueOf(3), iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    public void whenNoPlaceThenMustIncreaseCapacity() {
        IntStream.range(3, 10).forEach(v -> list.add(v));
    }

    @Test
    public void whenAddAfterGetIteratorThenMustBeException() {
       Iterator<Integer> iterator = list.iterator();
       list.add(4);
       assertThatThrownBy(iterator::next)
               .isInstanceOf(ConcurrentModificationException.class);
    }

    @Test
    public void whenRemoveAfterGetIteratorThenMustBeException() {
       Iterator<Integer> iterator = list.iterator();
       list.add(0);
       assertThatThrownBy(iterator::next)
               .isInstanceOf(ConcurrentModificationException.class);
    }

}
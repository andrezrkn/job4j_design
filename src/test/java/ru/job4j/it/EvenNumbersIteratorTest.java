package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

public class EvenNumbersIteratorTest {

    @Test
    public void shouldReturnEvenNumbersSequentially() {
        Iterator<Integer> it = new EvenNumbersIterator(
                new int[] {1, 2, 3, 4, 5, 6, 7});
        assertTrue(it.hasNext());
        assertEquals(it.next(), 2);
        assertTrue(it.hasNext());
        assertEquals(it.next(), 4);
        assertTrue(it.hasNext());
        assertEquals(it.next(), 6);
        assertFalse(it.hasNext());
        assertThatThrownBy(it::next)
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    public void sequentialHasNextInvocationDoesntAffectRetrievalOrder() {
        Iterator<Integer> it = new EvenNumbersIterator(
                new int[] {1, 2, 3, 4, 5, 6, 7});
        assertTrue(it.hasNext());
        assertTrue(it.hasNext());
        assertEquals(it.next(), 2);
        assertEquals(it.next(), 4);
        assertEquals(it.next(), 6);
    }

    @Test
    public void  shouldReturnFalseIfNoAnyEvenNumbers() {
        Iterator<Integer> it = new EvenNumbersIterator(new int[]{1});
        assertFalse(it.hasNext());
    }

    @Test
    public void allNumbersAreEven() {
        Iterator<Integer> it = new EvenNumbersIterator(new int[] {2, 4, 6, 8});
        assertTrue(it.hasNext());
        assertEquals(it.next(), 2);
        assertTrue(it.hasNext());
        assertEquals(it.next(), 4);
        assertTrue(it.hasNext());
        assertEquals(it.next(), 6);
        assertTrue(it.hasNext());
        assertEquals(it.next(), 8);
    }
}
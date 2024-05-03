package ru.job4j.it;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;
import java.util.NoSuchElementException;

public class BackwardArrayItTest {
    @Test
    public void whenMultiCallhasNextThenTrue() {
        BackwardArrayIt it = new BackwardArrayIt(
                new int[] {1, 2, 3}
        );
        assertTrue(it.hasNext());
        assertTrue(it.hasNext());
    }

    @Test
    public void whenReadSequence() {
        BackwardArrayIt it = new BackwardArrayIt(
                new int[] {1, 2, 3}
        );
        assertEquals(it.next(), 3);
        assertEquals(it.next(), 2);
        assertEquals(it.next(), 1);
    }

    @Test
    public void whenNextFromEmpty() {
        BackwardArrayIt it = new BackwardArrayIt(
                new int[] {}
        );
        assertThatThrownBy(it::next)
                .isInstanceOf(NoSuchElementException.class);
    }
}
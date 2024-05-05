package ru.job4j.iterator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

public class ListUtilsTest {

    @Test
    public void whenAddBefore() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 1, 2);
        assertEquals(input, Arrays.asList(1, 2, 3));
    }

    public void whenAddBeforeWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 3, 2);
        assertThatThrownBy(() -> ListUtils.addBefore(input, 3, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    public void whenAddAfterLast() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        ListUtils.addAfter(input, 2, 3);
        assertEquals(input, Arrays.asList(0, 1, 2, 3));
    }

    @Test
    public void whenRemoveAll() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5));
        ListUtils.removeAll(input, new ArrayList<>(Arrays.asList(4, 5)));
        assertEquals(input, Arrays.asList(0, 1, 2, 3));
    }

    @Test
    public void whenPredicateRemoveIf() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5));
        Predicate<Integer> predicate = s -> s.equals(5);
        ListUtils.removeIf(input, predicate);
        assertEquals(input, Arrays.asList(0, 1, 2, 3, 4));
    }

    @Test
    public void whenPredicateReplaceIf() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 5, 2, 5, 4, 5));
        Predicate<Integer> predicate = s -> s.equals(5);
        ListUtils.replaceIf(input, predicate, 10);
        assertEquals(input, Arrays.asList(0, 10, 2, 10, 4, 10));
    }
}
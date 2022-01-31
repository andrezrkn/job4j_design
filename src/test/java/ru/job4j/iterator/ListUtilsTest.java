package ru.job4j.iterator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.Matchers.is;
import org.junit.Test;
import java.util.function.Predicate;

import static org.junit.Assert.assertThat;


public class ListUtilsTest {

    @Test
    public void whenAddBefore() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 1, 2);
        assertThat(input, is(Arrays.asList(1, 2, 3)));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddBeforeWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 3, 2);
    }

    @Test
    public void whenAddAfterLast() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        ListUtils.addAfter(input, 2, 3);
        assertThat(input, is(Arrays.asList(0, 1, 2, 3)));
    }

    @Test
    public void whenRemoveAll() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5));
        ListUtils.removeAll(input, new ArrayList<>(Arrays.asList(4, 5)));
        assertThat(input, is(Arrays.asList(0, 1, 2, 3)));
    }

    @Test
    public void whenPredicateRemoveIf() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5));
        Predicate<Integer> predicate = s -> s.equals(5);
        ListUtils.removeIf(input, predicate);
        assertThat(input, is(Arrays.asList(0, 1, 2, 3, 4)));
    }

    @Test
    public void whenPredicateReplaceIf() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 5, 2, 5, 4, 5));
        Predicate<Integer> predicate = s -> s.equals(5);
        ListUtils.replaceIf(input, predicate, 10);
        assertThat(input, is(Arrays.asList(0, 10, 2, 10, 4, 10)));
    }
}
package ru.job4j.it;

import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.NoSuchElementException;
import static org.assertj.core.api.Assertions.*;

class CyclicIteratorTest {
    @Test
    public void whenEmptyThenHasNextIsFalse() {
        CyclicIterator<Integer> iterator = new CyclicIterator<>(List.of());
        assertThat(iterator.hasNext()).isFalse();
    }

    @Test
    public void whenEmptyAndeNextThenThrow() {
        CyclicIterator<Integer> iterator = new CyclicIterator<>(List.of());
        assertThatThrownBy(iterator::next)
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    public void whenNotEmptyThenHasNextIsTrue() {
        CyclicIterator<Integer> iterator = new CyclicIterator<>(List.of(1));
        assertThat(iterator.hasNext()).isTrue();
    }

    @Test
    public void whenNotEmptyThenSomeHasNextIsTrue() {
        CyclicIterator<Integer> iterator = new CyclicIterator<>(List.of(1));
        assertThat(iterator.hasNext()).isTrue();
        assertThat(iterator.hasNext()).isTrue();
    }

    @Test
    public void whenOneElementThenNext() {
        CyclicIterator<Integer> iterator = new CyclicIterator<>(List.of(1));
        assertThat(iterator.hasNext()).isTrue();
        assertThat(iterator.next()).isEqualTo(1);
        assertThat(iterator.hasNext()).isTrue();
        assertThat(iterator.next()).isEqualTo(1);
        assertThat(iterator.hasNext()).isTrue();
        assertThat(iterator.next()).isEqualTo(1);

    }

    @Test
    public void whenNotEmptyThenNext() {
        CyclicIterator<Integer> iterator = new CyclicIterator<>(List.of(1, 2, 3));
        assertThat(iterator.next()).isEqualTo(1);
        assertThat(iterator.hasNext()).isTrue();
        assertThat(iterator.next()).isEqualTo(2);
        assertThat(iterator.hasNext()).isTrue();
        assertThat(iterator.next()).isEqualTo(3);
        assertThat(iterator.hasNext()).isTrue();
        assertThat(iterator.next()).isEqualTo(1);
        assertThat(iterator.hasNext()).isTrue();
        assertThat(iterator.next()).isEqualTo(2);
        assertThat(iterator.hasNext()).isTrue();
        assertThat(iterator.next()).isEqualTo(3);
        assertThat(iterator.hasNext()).isTrue();
        assertThat(iterator.next()).isEqualTo(1);
        assertThat(iterator.hasNext()).isTrue();
    }
}
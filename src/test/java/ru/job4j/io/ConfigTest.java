package ru.job4j.io;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertEquals(config.value("name"), "Petr Arsentev");
        assertThatThrownBy(() -> config.value("surname"))
                .isInstanceOf(IllegalArgumentException.class);

    }

    @Test
    public void whenDataWithSpaceAndComment() {
        String path = "./data/data_with_space_and_comment.properties";
        Config config = new Config(path);
        config.load();
        assertEquals(config.value("2+2?"), "a skolko nado?");
    }

    @Test
    public void whenDataWithoutKey() {
        String path = "./data/data_without_key.properties";
        Config config = new Config(path);
        config.load();
        assertThatThrownBy(() -> config.value("123"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void whenLineHasSpace() {
        String path = "./data/line_has_space.properties";
        Config config = new Config(path);
        config.load();
        assertEquals(config.value("2+2?"), "a skolko nado?");
    }

    @Test
    public void whenLineHasInValueEqual() {
        String path = "./data/when_line_has_in_value_equal.properties";
        Config config = new Config(path);
        config.load();
        assertEquals(config.value("2+2?"), "a=skolko=nado?");
    }

    @Test
    public void whenEqualAtTheEnd() {
        String path = "./data/when_equal_at_the_end.properties";
        Config config = new Config(path);
        config.load();
        assertEquals(config.value("5+5"), "10=");
    }

    @Test
    public void whenKeyWithoutValue()  {
        String path = "./data/when_key_without_value.properties";
        Config config = new Config(path);
        config.load();
        assertThatThrownBy(() -> config.value("5+5"))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
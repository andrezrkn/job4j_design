package ru.job4j.io;

import org.hamcrest.Matchers;
import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("Petr Arsentev"));
        assertThat(config.value("surname"), is(Matchers.nullValue()));
    }

    @Test
    public void whenDataWithSpaceAndComment() {
        String path = "./data/data_with_space_and_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("2+2?"), is("a skolko nado?"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenDataWithoutKey() {
        String path = "./data/data_without_key.properties";
        Config config = new Config(path);
        config.load();
    }

    @Test
    public void whenLineHasSpace() {
        String path = "./data/line_has_space.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("2+2?"), is("a skolko nado?"));
    }
}
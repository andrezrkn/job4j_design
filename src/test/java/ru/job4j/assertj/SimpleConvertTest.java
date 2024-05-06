package ru.job4j.assertj;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.assertj.core.data.Index;
import java.util.List;
import java.util.Map;
import java.util.Set;

class SimpleConvertTest {
    @Test
    public void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    public void checkList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert.toList("first", "second", "three", "four", "five");
        assertThat(list).first()
                .isEqualTo("first");
        assertThat(list).last()
                .isEqualTo("five");
        assertThat(list).element(3)
                .isEqualTo("four");
        assertThat(list).allMatch(e -> !e.contains("a"))
                .anyMatch(e -> e.contains("thr"));
        assertThat(list).filteredOn(e -> e.contains("i"))
                .last()
                .isEqualTo("five");
    }

    @Test
    public void checkSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> set = simpleConvert.toSet("ford", "mazda", "lamborghini", "ferrari",
                "porsche", "kia", "ford", "lada", "audi");
        assertThat(set).filteredOn(e -> e.contains("l") && e.contains("d"))
                .hasSize(1);
    }

    @Test
    public void checkMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> map = simpleConvert.toMap("ford", "mazda", "lamborghini", "ferrari",
                "porsche", "kia", "ford", "lada", "audi");
        assertThat(map).doesNotContainValue(9)
                .hasSize(8);
        assertThat(map.values()).filteredOn(e -> e < 5)
                .hasSize(5);
    }
}
package ru.job4j.assertj;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NameLoadTest {

    @Test
    public void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }

    @Test
    public void ifParseIsEmptyTest() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::parse)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Names array is empty");
    }

    @Test
    public void ifValidateDoesNotContainTheEqualitySymbol() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse("1", "2", "3"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("the symbol '='");
    }

    @Test
    public void ifValidateDoesNotContainAKey() {
        NameLoad nameload = new NameLoad();
        assertThatThrownBy(() -> nameload.parse("=1"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("contain a key")
                .hasMessageContaining("=1");
    }

    @Test
    public void ifValidateDoesNotContainAValue() {
        NameLoad nameload = new NameLoad();
        assertThatThrownBy(() -> nameload.parse("1="))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("contain a value")
                .hasMessageContaining("1=");
    }
}
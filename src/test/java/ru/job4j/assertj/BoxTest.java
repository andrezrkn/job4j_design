package ru.job4j.assertj;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class BoxTest {
    @Test
    public void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere");
    }

    @Test
    public void isThisUnknown() {
        Box box = new Box(3, 2);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Unknown object");
    }

    @Test
    public void getNumberOfVerticesIsZero() {
        Box box = new Box(0, 6);
        int count = box.getNumberOfVertices();
        assertThat(count).isEqualTo(0);
    }

    @Test
    public void getNumberOfVerticesIsFour() {
        Box box = new Box(4, 6);
        int count = box.getNumberOfVertices();
        assertThat(count).isEqualTo(4);
    }

    @Test
    public void getNumberOfVerticesIsEight() {
        Box box = new Box(8, 12);
        int count = box.getNumberOfVertices();
        assertThat(count).isEqualTo(8);
    }

    @Test
    public void isExistTrue() {
        Box box = new Box(8, 12);
        assertTrue(box.isExist());
    }

    @Test
    public void isExistFalse() {
        Box box = new Box(-5, -9);
        assertFalse(box.isExist());
    }

    @Test
    public void getAreaOfSphereIsTrue() {
        Box box = new Box(0, 6);
        assertThat(box.getArea()).isLessThan(460d)
                .isEqualTo(452.38d, withPrecision(0.01d));
    }

    @Test
    public void getAreaOfTetrahedronIsTrue() {
        Box box = new Box(4, 6);
        assertThat(box.getArea()).isLessThan(65d)
                .isEqualTo(62.35d, withPrecision(0.01d));
    }

    @Test
    public void getAreaOfCubeIsTrue() {
        Box box = new Box(8, 6);
        assertThat(box.getArea()).isLessThan(220d)
                .isEqualTo(216d);
    }

    @Test
    public void getAreaOfUnknownObjectIsTrue() {
        Box box = new Box(-1, -1);
        assertThat(box.getArea()).isEqualTo(0);
    }
}
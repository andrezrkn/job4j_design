package ru.job4j.generic;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RoleStoreTest {

    @Test
    public void testOne() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Admin №1"));
        Role result = store.findById("1");
        assertEquals(result.getRolename(), "Admin №1");
    }

    @Test
    public void testTwo() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Admin №1"));
        store.add(new Role("2", "Admin №2"));
        store.add(new Role("3", "Super Admin №1"));
        store.replace("2", new Role("2", "User №1"));
        Role result = store.findById("2");
        assertEquals(result.getRolename(), "User №1");
    }
}
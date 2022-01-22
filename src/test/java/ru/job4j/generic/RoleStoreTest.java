package ru.job4j.generic;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RoleStoreTest {

    @Test
    public void Test1() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Admin №1"));
        Role result = store.findById("1");
        assertThat(result.getRolename(), is("Admin №1"));
    }

    @Test
    public void Test2() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Admin №1"));
        store.add(new Role("2", "Admin №2"));
        store.add(new Role("3", "Super Admin №1"));
        store.replace("2", new Role("2", "User №1"));
        Role result = store.findById("2");
        assertThat(result.getRolename(), is("User №1"));
    }
}
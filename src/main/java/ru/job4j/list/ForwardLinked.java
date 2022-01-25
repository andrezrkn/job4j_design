package ru.job4j.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinked<T> implements Iterable<T> {

    private Node<T> head;

    public void add(T value) {
        Node<T> node = new Node<T>(value, null);
        if (head == null) {
            head = node;
            return;
        }
        Node<T> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
    }

    public boolean revert() {
        Node<T> prev = null;
        Node<T> now;
        boolean rsl = head != null && head.next != null;
        if (rsl) {
            do {
                now = head.next;
                head.next = prev;
                prev = head;
                head = now;
                rsl = true;
            } while (head.next != null);
            head.next = prev;
        }
        return rsl;
    }

    public void addFirst(T value) {
        head = new Node<T>(value, head);
    }

    public T deleteFirst() {
        Node<T> el;
        if (head == null) {
            throw new NoSuchElementException();
        }
        el = head;
        head = head.next;
        el.next = null;
        return el.value;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    private static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}

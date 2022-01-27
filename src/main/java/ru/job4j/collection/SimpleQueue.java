package ru.job4j.collection;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    private int countIn = 0;
    private int countOut = 0;

    public T poll() {
        while (countIn != 0) {
            out.push(in.pop());
            countIn--;
            countOut++;
        }
        countOut--;
        return out.pop();
    }

    public void push(T value) {
        while (countOut != 0) {
            in.push(out.pop());
            countIn++;
            countOut--;
        }
        in.push(value);
        countIn++;
    }
}

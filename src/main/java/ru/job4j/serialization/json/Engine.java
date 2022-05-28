package ru.job4j.serialization.json;

public class Engine {
    private final String name;
    private final int ePower;
    private final int weight;

    public Engine(String name, int ePower, int weight) {
        this.name = name;
        this.ePower = ePower;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Engine{"
                + "name='" + name + '\''
                + ", ePower=" + ePower
                + ", weight=" + weight
                + '}';
    }
}

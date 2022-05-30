package ru.job4j.serialization.json;

import java.util.Arrays;

public class Car {
    private final boolean operativeCondition;
    private final int weight;
    private final String name;
    private final Engine engine;
    private final String[] competitors;

    public Car(boolean operativeCondition, int weight, String name,
               Engine engine, String[] competitors) {
        this.operativeCondition = operativeCondition;
        this.weight = weight;
        this.name = name;
        this.engine = engine;
        this.competitors = competitors;
    }

    @Override
    public String toString() {
        return "Car{"
                + "operativeCondition=" + operativeCondition
                + ", weight=" + weight
                + ", name='" + name + '\''
                + ", engine=" + engine
                + ", competitors='" + Arrays.toString(competitors) + '\''
                + '}';
    }

    public boolean isOperativeCondition() {
        return operativeCondition;
    }

    public int getWeight() {
        return weight;
    }

    public String getName() {
        return name;
    }

    public Engine getEngine() {
        return engine;
    }

    public String[] getCompetitors() {
        return competitors;
    }
}

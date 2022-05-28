package ru.job4j.serialization.xml;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "engine")
public class Engine {
    @XmlAttribute
    private String name;
    @XmlAttribute
    private int ePower;
    @XmlAttribute
    private int weight;

    public Engine(String name, int ePower, int weight) {
        this.name = name;
        this.ePower = ePower;
        this.weight = weight;
    }

    public Engine() {
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

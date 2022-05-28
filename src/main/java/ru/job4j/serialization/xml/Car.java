package ru.job4j.serialization.xml;

import javax.xml.bind.annotation.*;
import java.util.Arrays;

@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
public class Car {
    @XmlAttribute
    private boolean operativeCondition;
    @XmlAttribute
    private int weight;
    @XmlAttribute
    private String name;
    private Engine engine;
    @XmlElementWrapper(name = "competitors")
    @XmlElement(name = "competitor")
    private String[] competitors;

    public Car(boolean operativeCondition, int weight, String name,
               Engine engine, String[] competitors) {

        this.operativeCondition = operativeCondition;
        this.weight = weight;
        this.name = name;
        this.engine = engine;
        this.competitors = competitors;
    }

    public Car() {
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
}

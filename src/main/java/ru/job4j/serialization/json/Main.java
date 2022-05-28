package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {
    public static void main(String[] args) {
        final Car car = new Car(true, 2000, "volvo",
                new Engine("T4", 250, 350), new String[] {"Ford", "Volkswagen", "Saab"});

        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(car));
    }
}

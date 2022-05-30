package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        final Engine engine = new Engine("T4", 250, 350);
        final Car car = new Car(true, 2000, "volvo",
                engine, new String[] {"Ford", "Volkswagen", "Saab"});

        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(car));

        JSONObject jsonEngine = new JSONObject();
        jsonEngine.put("name", engine.getName());
        jsonEngine.put("ePower", engine.getePower());
        jsonEngine.put("weight", engine.getWeight());

        List<String> competitors = new ArrayList<>();
        competitors.add("Ford");
        competitors.add("Volkswagen");
        JSONArray jsonCompetitors = new JSONArray(competitors);

        JSONObject jsonCar = new JSONObject();
        jsonCar.put("operativeCondition", car.isOperativeCondition());
        jsonCar.put("weight", car.getWeight());
        jsonCar.put("name", car.getName());
        jsonCar.put("engine", jsonEngine);
        jsonCar.put("competitors", jsonCompetitors);

        System.out.println(jsonCar.toString());
    }
}

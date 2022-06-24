package ru.job4j.jdbc;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

import ru.job4j.io.Config;

public class ConnectionDemo {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Config c = new Config("./src/main/resources/app.properties");
        c.load();
        Map<String, String> info = c.getValues();
        Class.forName(info.get("driver"));
        try (Connection connection = DriverManager.getConnection(info.get("url"),
                             info.get("login"), info.get("password"))) {
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println(metaData.getUserName());
            System.out.println(metaData.getURL());
        }
    }
}
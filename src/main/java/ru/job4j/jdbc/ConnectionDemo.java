package ru.job4j.jdbc;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

import ru.job4j.io.Config;

public class ConnectionDemo {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Config c = new Config("./src/main/resources/app.properties");
        c.load();
        Class.forName(c.value("driver"));
        try (Connection connection = DriverManager.getConnection(c.value("url"),
                             c.value("login"), c.value("password"))) {
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println(metaData.getUserName());
            System.out.println(metaData.getURL());
        }
    }
}
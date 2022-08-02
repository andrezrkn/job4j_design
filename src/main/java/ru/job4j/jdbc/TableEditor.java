package ru.job4j.jdbc;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private Properties properties;

    public TableEditor(Properties properties) throws Exception {
        this.properties = properties;
        initConnection();
    }

    private void execute(String sql) throws Exception {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        }
    }

    private void initConnection() throws Exception {
        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream("app.properties")) {
            properties.load(in);
        }
    }

    public void createTable(String tableName) throws Exception {
        String sql = String.format(
                "CREATE TABLE IF NOT EXISTS %s()",
                tableName
        );
        execute(sql);
    }

    public void dropTable(String tableName) throws Exception {
        String sql = String.format(
                "DROP TABLE IF EXISTS %s",
                tableName
        );
        execute(sql);
    }

    public void addColumn(String tableName, String columnName, String type) throws Exception {
        String sql = String.format(
                "ALTER TABLE %s ADD COLUMN %s %s",
                tableName, columnName, type
        );
        execute(sql);
    }

    public void dropColumn(String tableName, String columnName) throws Exception {
        String sql = String.format(
                "ALTER TABLE %s DROP COLUMN %s",
                tableName, columnName
        );
        execute(sql);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) throws Exception {
        String sql = String.format(
                "ALTER TABLE %s RENAME COLUMN %s TO  %s",
                tableName, columnName, newColumnName
        );
        execute(sql);
    }


    public static String getTableScheme(Connection connection, String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    public void sysOut(String name) throws Exception {
        System.out.println(getTableScheme(connection, name));
    }
    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws Exception {
        Properties config = new Properties();
        try (var te = new TableEditor(config)) {
            te.initConnection();
            te.connection = DriverManager.getConnection(
                    te.properties.getProperty("url"),
                    te.properties.getProperty("login"),
                    te.properties.getProperty("password")
            );
            te.createTable("Car");
            te.sysOut("Car");

            te.addColumn("Car", "Engine", "text");
            te.sysOut("Car");

            te.renameColumn("Car", "Engine", "Body");
            te.sysOut("Car");

            te.dropColumn("Car", "Body");
            te.sysOut("Car");

            te.dropTable("Car");
            te.sysOut("Car");
        }
    }
}
package jm.task.core.jdbc;

import java.sql.*;

public class Main {
    private static final String URL = "jdbc:mysql://localhost:3306/mydbtest";
    private static final String USER = "root";
    private static final String PASSWORD = "Disko1.3";

    public static void main(String[] args) {
        try (Connection connection= DriverManager.getConnection(URL,USER,PASSWORD); Statement statement = connection.createStatement()){
            statement.execute("INSERT INTO users(name,lastname,age) VALUES ('vlad', 'sushintsev', 25)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

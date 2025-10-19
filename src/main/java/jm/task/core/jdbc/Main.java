package jm.task.core.jdbc;

import java.sql.*;

public class Main {
    private static final String URL = "jdbc:mysql://localhost:3306/mydbtest";
    private static final String USER = "root";
    private static final String PASSWORD = "Disko1.3";
    public static Connection connection = null;
    public static Statement statement = null;

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();
    }

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            statement = connection.createStatement();
            System.out.println("Connection OK");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Connection ERROR");
        }

        return connection;
    }
}


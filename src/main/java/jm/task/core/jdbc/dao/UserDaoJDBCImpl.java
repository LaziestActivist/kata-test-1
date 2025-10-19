package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static jm.task.core.jdbc.Main.statement;

public class UserDaoJDBCImpl implements UserDao {
    private final Connection connection;

    public UserDaoJDBCImpl(Connection connection) {
        this.connection = connection;
    }

    public void createUsersTable() throws SQLException {
        String ex = "CREATE TABLE IF NOT EXISTS `mydbtest`.`Users` (\n" +
                "  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,\n" +
                "  `name` CHAR(40) NOT NULL,\n" +
                "  `lastname` VARCHAR(45) NOT NULL,\n" +
                "  `age` INT UNSIGNED NOT NULL,\n" +
                "  PRIMARY KEY (`id`)\n" +
                ");";;
        statement.execute(ex);
    }

    public void dropUsersTable() throws SQLException {
        statement.executeUpdate("DROP TABLE IF EXISTS Users");
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Users (name, lastname, age)VALUES (?, ?, ?)");
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, lastName);
        preparedStatement.setByte(3, age);
        preparedStatement.executeUpdate();
        System.out.println("User с именем — " + name + " добавлен в базу данных");
    }

    public void removeUserById(long id) throws SQLException {
        statement.executeUpdate("DELETE FROM Users WHERE id = " + id );
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery("SELECT * FROM Users");

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong(1));
                user.setName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setAge(resultSet.getByte(4));

                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return users;
    }

    public void cleanUsersTable() {
        try {
            statement.execute("TRUNCATE TABLE Users");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

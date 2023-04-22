package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        try (Statement statement = Util.getConnectionJDBC().createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS dbUsers " +
                    "(id BIGINT PRIMARY KEY AUTO_INCREMENT," +
                    "name VARCHAR (40) NOT NULL," +
                    "lastName VARCHAR (40) NOT NULL," +
                    "age TINYINT UNSIGNED NOT NULL)");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        try (Statement statement = Util.getConnectionJDBC().createStatement()) {
            statement.execute("DROP TABLE IF EXISTS dbUsers");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO dbUsers (name, lastName, age) Values (?, ?, ?)";
        try (PreparedStatement preparedStatement = Util.getConnectionJDBC().prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        String sql = "DELETE FROM dbUsers WHERE id = ?";
        try (PreparedStatement preparedStatement = Util.getConnectionJDBC().prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        List<User> result = new ArrayList<>();
        try (Statement statement = Util.getConnectionJDBC().createStatement()) {
            statement.execute("SELECT * FROM dbusers");
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                result.add(new User(resultSet.getString("name"), resultSet.getString("lastName"),
                        resultSet.getByte("age")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public void cleanUsersTable() {
        try (Statement statement = Util.getConnectionJDBC().createStatement()) {
            statement.execute("TRUNCATE dbUsers");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

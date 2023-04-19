package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    public static Connection getConnection() throws SQLException {
        String USER = "dementyDD";
        String PASSWORD = "Technodvij1990";
        String URL = "jdbc:mysql://localhost:3306/dementytest";
        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        return connection;
    }
}



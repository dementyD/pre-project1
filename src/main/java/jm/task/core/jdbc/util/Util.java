package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    public static Connection getConnectionJDBC() throws SQLException {
        String USER = "dementyDD";
        String PASSWORD = "Technodvij1990";
        String URL = "jdbc:mysql://localhost:3306/dementytest";
        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        return connection;
    }

    public static SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(User.class);

        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        configuration.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/dementytest");
        configuration.setProperty("hibernate.connection.username", "dementyDD");
        configuration.setProperty("hibernate.connection.password", "Technodvij1990");
        configuration.setProperty("hibernate.hbm2ddl.auto", "update");

        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();

        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);

        return sessionFactory;
    }
}



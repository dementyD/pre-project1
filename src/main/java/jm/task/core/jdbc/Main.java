package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        UserService userService = new UserServiceImpl();

        userService.createUsersTable();
        userService.saveUser("Denis", "Denisov", (byte) 21);
        System.out.println(" User с именем – Denis добавлен в базу данных");
        userService.saveUser("Kirill", "Brill", (byte) 22);
        System.out.println(" User с именем – Kirill добавлен в базу данных");
        userService.saveUser("Vartan", "Ivanov", (byte) 23);
        System.out.println(" User с именем – Vartan добавлен в базу данных");
        userService.saveUser("Anya", "AnyaAnya", (byte) 24);
        System.out.println(" User с именем – Anya добавлен в базу данных");

        System.out.println(userService.getAllUsers().toString());

        userService.cleanUsersTable();
        userService.dropUsersTable();

    }
}

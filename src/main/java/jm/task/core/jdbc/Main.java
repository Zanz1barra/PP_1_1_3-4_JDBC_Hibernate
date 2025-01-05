package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceJDBCImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserService table = new UserServiceJDBCImpl();
        User[] users = {
                new User("Феликс", "Дзержинский", (byte) 41),
                new User("Владимир", "Ульянов", (byte) 48),
                new User("Надежда", "Крупская", (byte) 49),
                new User("Фёдор", "Сергеев", (byte) 35)
        };
        table.dropUsersTable();
        table.createUsersTable();
        for (User user: users) {
            table.saveUser(user.getName(), user.getLastName(), user.getAge());
        }
        List<User> tableAllUsers = table.getAllUsers();
        for (User user: tableAllUsers) {
            System.out.println(user);
        }
        table.cleanUsersTable();
        table.dropUsersTable();
    }
}

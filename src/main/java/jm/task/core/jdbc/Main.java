package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl table = new UserServiceImpl();
        User user1 = new User("Феликс", "Дзержинский", (byte) 41);
        User user2 = new User("Владимир", "Ульянов", (byte) 48);
        User user3 = new User("Надежда", "Крупская", (byte) 49);
        User user4 = new User("Фёдор", "Сергеев", (byte) 35);
        table.dropUsersTable();
        table.createUsersTable();
        table.saveUser(user1.getName(), user1.getLastName(), user1.getAge());
        table.saveUser(user2.getName(), user2.getLastName(), user2.getAge());
        table.saveUser(user3.getName(), user3.getLastName(), user3.getAge());
        table.saveUser(user4.getName(), user4.getLastName(), user4.getAge());
        for (int i = 0; i < table.getAllUsers().size(); i++) {
            System.out.println(table.getAllUsers().get(i).toString());
        }
        table.cleanUsersTable();
        table.dropUsersTable();
    }
}

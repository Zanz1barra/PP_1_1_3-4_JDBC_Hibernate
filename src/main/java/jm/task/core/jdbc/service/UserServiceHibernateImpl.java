package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

// TODO логи перенеси в слой сервиса, только необходимые по ТЗ, остальные удали
public class UserServiceHibernateImpl implements UserService {
    // НЕ правильно, работай на уровне абстракции, а не реализации,
    // объявляй объект по типу интерфейса, например UserDao userDao =
    // затем присваивай реализацию, добавь модификатор доступа,
    // изучи полиморфизм и слабую связанность
    private UserDao dao;

    public UserServiceHibernateImpl() {
        dao = new UserDaoHibernateImpl();
    }

    public void createUsersTable() {
        dao.createUsersTable();
    }

    public void dropUsersTable() {
        dao.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        dao.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        dao.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return dao.getAllUsers();
    }

    public void cleanUsersTable() {
        dao.cleanUsersTable();
    }
}

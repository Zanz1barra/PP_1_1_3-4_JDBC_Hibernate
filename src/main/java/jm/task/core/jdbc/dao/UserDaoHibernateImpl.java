package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.CloseableTransaction;
import jm.task.core.jdbc.util.Util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private final SessionFactory sessionFactory;

    public UserDaoHibernateImpl() {
        sessionFactory = Util.getSessionFactory();
    }

    private void executeSqlCommand(String sqlCommand) {
        try (Session session = sessionFactory.openSession();
             CloseableTransaction transaction = new CloseableTransaction(session)) {
            session.createSQLQuery(sqlCommand).addEntity(User.class).executeUpdate();
            transaction.commit();
        }
    }

    @Override
    public void createUsersTable() {
        String sqlCreateCommand = "CREATE TABLE IF NOT EXISTS " + Util.USER_TABLE_NAME + " " +
                "(id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(50) NOT NULL, lastName VARCHAR(50) NOT NULL, " +
                "age TINYINT NOT NULL)";

        executeSqlCommand(sqlCreateCommand);
    }

    @Override
    public void dropUsersTable() {
        String sqlDropCommand = "DROP TABLE IF EXISTS " + Util.USER_TABLE_NAME;
        executeSqlCommand(sqlDropCommand);
    }

    public void saveUser(User user) {
        try (Session session = sessionFactory.openSession();
             CloseableTransaction transaction = new CloseableTransaction(session)) {
            session.save(user);
            transaction.commit();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        saveUser(new User(name, lastName, age));
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = sessionFactory.openSession()) {
            User user = session.load(User.class, id);
            session.delete(user);
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = sessionFactory.openSession()) {
            return (List<User>) session.createQuery("From " + User.class.getName()).list();
        }
    }

    @Override
    public void cleanUsersTable() {
        String sqlCleanCommand = "DELETE FROM " + Util.USER_TABLE_NAME;
        executeSqlCommand(sqlCleanCommand);
    }
}

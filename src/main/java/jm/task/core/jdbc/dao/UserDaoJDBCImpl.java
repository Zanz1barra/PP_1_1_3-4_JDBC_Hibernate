package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    Connection conn;

    public UserDaoJDBCImpl() throws SQLException {
        this.conn = Util.getMySQLConnection();
    }

    public void createUsersTable() throws SQLException {
        ResultSet resultSet = conn.getMetaData()
                .getTables(null, null, Util.USER_TABLE_NAME, null);
        if (!resultSet.next()) {
            String usersTable = "CREATE TABLE " + Util.USER_TABLE_NAME + " ("
                    + "id INT NOT NULL AUTO_INCREMENT,"
                    + "name VARCHAR(45) NOT NULL,"
                    + "lastName VARCHAR(45) NOT NULL,"
                    + "age INT NOT NULL,"
                    + "PRIMARY KEY (id))"
                    + "ENGINE = InnoDB AUTO_INCREMENT = 1";
            conn.createStatement()
                    .executeUpdate(usersTable);
        }else {
            System.out.println("Таблица уже существует");
        }
    }

    public void dropUsersTable() throws SQLException {
        ResultSet resultSet = conn.getMetaData()
                .getTables(null, null, Util.USER_TABLE_NAME, null);
        if (resultSet.next()) {
            String query = "DROP TABLE " + Util.USER_TABLE_NAME;
            conn.createStatement()
                    .executeUpdate(query);
        } else {
            System.out.println("Таблица уже удалена");
        }
    }


    public void saveUser(String name, String lastName, byte age) throws SQLException {
        conn.createStatement()
                .executeUpdate("INSERT INTO " + Util.USER_TABLE_NAME + "(name, lastName, age) " +
                        "VALUES (" + "'" + name + "'" + ", " + "'" + lastName + "'" + ", " + age + ")");
        System.out.println("User с именем - " + name + " " + lastName + " " + "добавлен в базу данных.");
    }

    public void removeUserById(long id) throws SQLException {
        conn.createStatement()
                .executeUpdate("DELETE FROM " + Util.USER_TABLE_NAME + " WHERE ID = " + id);
    }

    public List<User> getAllUsers() throws SQLException {
        ResultSet resultSet = conn.createStatement()
                .executeQuery("SELECT id, name, lastName, age FROM " + Util.USER_TABLE_NAME);
        List<User> list = new ArrayList<>();
        while (resultSet.next()){
            User user = new User(resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getByte(4));
            user.setId(resultSet.getLong(1));
            list.add(user);
        }
        return list;
    }

    public void cleanUsersTable() throws SQLException {
        conn.createStatement()
                .executeUpdate("DELETE FROM " + Util.USER_TABLE_NAME);
    }
}

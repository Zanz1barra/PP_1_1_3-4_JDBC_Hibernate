package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.TimeZone;


public class Util {
    public static final String DATABASE_DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String DATABASE_DIALECT = "org.hibernate.dialect.MySQL5InnoDBDialect";

    public static final String DEFAULT_HOST_NAME = "127.0.0.1";
    public static final String DEFAULT_DB_NAME = "mysql";
    public static final String DEFAULT_USER_NAME = "root";
    public static final String DEFAULT_PASSWORD = "root";

    public static final String USER_TABLE_NAME = "UserData";

    public static SessionFactory sessionFactory;

    private static String getConnectionURL(String hostName, String dbName) {
        return "jdbc:mysql://" + hostName + ":3306/" + dbName;
    }

    // Connect to MySQL
    public static Connection getMySQLConnection(String hostName, String dbName,
                                                String userName, String password) throws SQLException {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        return DriverManager.getConnection(getConnectionURL(hostName, dbName), userName, password);
    }

    public static Connection getMySQLConnection() throws SQLException {
        return getMySQLConnection(DEFAULT_HOST_NAME, DEFAULT_DB_NAME, DEFAULT_USER_NAME, DEFAULT_PASSWORD);
    }

    public static SessionFactory getSessionFactory(String hostName, String dbName,
                                                   String userName, String password) {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                Properties settings = new Properties();

                settings.put(Environment.DRIVER, DATABASE_DRIVER);
                settings.put(Environment.URL, getConnectionURL(hostName, dbName));
                settings.put(Environment.USER, userName);
                settings.put(Environment.PASS, password);
                settings.put(Environment.DIALECT, DATABASE_DIALECT);
                settings.put(Environment.SHOW_SQL, "true");
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

                configuration.setProperties(settings);
                configuration.addAnnotatedClass(User.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                System.out.println("Problem creating session factory");
                e.printStackTrace();
            }
        }

        return sessionFactory;
    }

    public static SessionFactory getSessionFactory() {
        return getSessionFactory(DEFAULT_HOST_NAME, DEFAULT_DB_NAME, DEFAULT_USER_NAME, DEFAULT_PASSWORD);
    }
}

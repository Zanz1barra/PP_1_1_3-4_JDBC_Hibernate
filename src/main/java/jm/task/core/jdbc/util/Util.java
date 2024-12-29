package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.TimeZone;


public class Util {
    public static final String DEFAULT_HOST_NAME = "127.0.0.1";
    public static final String DEFAULT_DB_NAME = "mysql";
    public static final String DEFAULT_USER_NAME = "root";
    public static final String DEFAULT_PASSWORD = "root";

    public static final String USER_TABLE_NAME = "UserData";

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
        return getMySQLConnection(defaultHostName, defaultDbName, defaultUserName, defaultPassword);
    }
}

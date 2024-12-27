package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.TimeZone;


public class Util {
    public static String defaultHostName = "127.0.0.1";
    public static String defaultDbName = "mysql";
    public static String defaultUserName = "root";
    public static String defaultPassword = "root";

    public static String userTableName = "UserData";

    // Connect to MySQL
    public static Connection getMySQLConnection(String hostName, String dbName,
                                                String userName, String password) throws SQLException {
        String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName;
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        return DriverManager.getConnection(connectionURL, userName, password);
    }

    public static Connection getMySQLConnection() throws SQLException {
        return getMySQLConnection(defaultHostName, defaultDbName, defaultUserName, defaultPassword);
    }
}

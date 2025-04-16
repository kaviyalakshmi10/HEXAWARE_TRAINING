package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnUtil {

    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                Properties props = DBPropertyUtil.getPropertyString("src\\database.properties");
                String url = props.getProperty("db.url");
                String user = props.getProperty("db.user");
                String password = props.getProperty("db.password");

                connection = DriverManager.getConnection(url, user, password);
                System.out.println("Database connection successful!");
            } catch (SQLException e) {
                System.out.println("Database connection failed: " + e.getMessage());
            }
        }
        return connection;
    }
}

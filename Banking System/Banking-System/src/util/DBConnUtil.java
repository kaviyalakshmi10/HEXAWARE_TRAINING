package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnUtil {
    public static Connection getConnection(Properties props) {
        Connection connection = null;
        try {
            String username = props.getProperty("username").trim();
            String password = props.getProperty("password").trim();
            String driver = props.getProperty("driver").trim();
            String url = props.getProperty("url").trim();

            Class.forName(driver);
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("✅ Connection established successfully.");
        } catch (ClassNotFoundException e) {
            System.err.println("❌ JDBC Driver not found: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("❌ SQL error: " + e.getMessage());
        }
        return connection;
    }
}

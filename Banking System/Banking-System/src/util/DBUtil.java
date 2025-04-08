package util;

import java.sql.Connection;
import java.util.Properties;

public class DBUtil {

    public static Connection getDBConn() {
        Connection connection = null;

        // Update the path as per your actual location if needed
        String filePath = "D:\\Download\\Java-Assignment\\Banking-System\\src\\database.properties"; // or use an absolute path if needed

        // Load properties
        Properties props = DBPropertyUtil.loadProperty(filePath);

        // Get connection
        connection = DBConnUtil.getConnection(props);

        return connection;
    }
}

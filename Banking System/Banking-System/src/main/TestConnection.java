package main;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestConnection {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Loaded driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "admin");
            System.out.println("Connected to MySQL");
            con.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
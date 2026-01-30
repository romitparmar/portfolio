package utils;

import java.sql.*;


public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost/cricket_shop";
    private static final String USER = "root";   
    private static final String PASS = "";         

    public static Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(URL, USER, PASS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }
}

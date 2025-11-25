package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

    // CHANGE these if your DB name / user / password are different
    private static final String URL  = "jdbc:mysql://localhost:3306/estore4413?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";       // your MySQL username
    private static final String PASS = "EECS4413";   // your MySQL password

    // Optional: explicitly load the driver (safe with older JDBC)
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}

package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionUtil {

    private static final String DATABASE_URL = "jdbc:oracle:thin:@192.168.6.21:1521:dblabs";
    private static final String USERNAME = "iee2020202";
    private static final String PASSWORD = "Fuckyou69420@";  // Replace with your actual password

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
    }
}
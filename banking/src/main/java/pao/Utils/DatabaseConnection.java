package pao.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static Connection connection = null;

    public static Connection getConnection() {
        if (DatabaseConnection.connection == null) {
            try {
                String url = "jdbc:mysql://localhost:3306/bank";
                String user = "root";
                String password = "1234";

                DatabaseConnection.connection = DriverManager.getConnection(url, user, password);
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
        return DatabaseConnection.connection;
    }

    public static void closeConnection() {
        try {
            assert connection != null;
            connection.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}

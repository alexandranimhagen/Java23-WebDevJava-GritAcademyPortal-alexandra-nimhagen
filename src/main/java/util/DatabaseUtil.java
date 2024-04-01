package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {
    // URL till MySQL databas
    private static final String URL = "jdbc:mysql://localhost:3306/GritAcademy";

    private static final String USER = "user";

    private static final String PASSWORD = "password";


    public static Connection getConnection() throws SQLException {
        try {
            // Laddar JDBC-drivrutinen för MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            // Skriver ut eventuella felmeddelanden
            e.printStackTrace();
            // SQLException om det uppstår ett fel vid anslutning till databasen
            throw new SQLException("Failed to establish connection to the database.");
        }
    }

    // Metod för att stänga anslutning till databasen
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                // Stänger anslutningen om den inte är null
                connection.close();
            } catch (SQLException e) {
                // Skriver ut felmeddelanden om det uppstår fel vid stängning av anslutningen
                e.printStackTrace();
            }
        }
    }
}

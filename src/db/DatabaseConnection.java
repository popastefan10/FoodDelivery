package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static Connection connection = null;

    private DatabaseConnection() {
    }

    public static Connection getInstance() {
        if (connection == null) {
            // Load the driver class
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException e) {
                System.out.println("Unable to load the driver: " + e.getMessage());
                System.exit(-1);
            }

            // Get the connection
            try {
                String url = "jdbc:postgresql://localhost:5432/FoodDelivery";
                String user = "food-delivery";
                String password = "1234";

                connection = DriverManager.getConnection(url, user, password);
                if (connection != null) {
                    System.out.println("Connection established!");
                }
                else {
                    System.out.println("Connection failed!");
                }
            }
            catch (SQLException e) {
                System.out.println("Unable to create a connection: " + e.getMessage());
            }
        }

        return connection;
    }
}

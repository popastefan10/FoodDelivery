package repositories;

import db.DatabaseConnection;
import models.Driver;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class DriverRepository implements GenericRepository<Driver> {
    private static DriverRepository instance = null;
    private final Connection connection;

    private DriverRepository() {
        connection = DatabaseConnection.getInstance();
    }

    public static DriverRepository getInstance() {
        if (instance == null)
            instance = new DriverRepository();
        return instance;
    }

    public Driver create(Driver driver) {
        String sql = "INSERT INTO drivers (id, email, \"phone-number\", \"first-name\", \"last-name\", rating) " +
                "VALUES" +
                "(DEFAULT, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, driver.getEmail());
            statement.setString(2, driver.getPhoneNumber());
            statement.setString(3, driver.getFirstName());
            statement.setString(4, driver.getLastName());
            statement.setFloat(5, driver.getRating());
            statement.executeUpdate();
            statement.close();

            return driver;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

    public Map<Integer, Driver> getAll() {
        var drivers = new HashMap<Integer, Driver>();
        String sql = "SELECT * FROM drivers";

        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                var driver = new Driver(result.getString(2), result.getString(3),
                                        result.getString(4), result.getString(5),
                                        result.getFloat(6));
                driver.setId(result.getInt(1));
                drivers.put(driver.getId(), driver);
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return drivers;
    }

    public Driver getById(Integer id) {
        String sql = "SELECT * FROM drivers WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                var driver = new Driver(result.getString(2), result.getString(3),
                                        result.getString(4), result.getString(5),
                                        result.getFloat(6));
                driver.setId(result.getInt(1));
                statement.close();

                return driver;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public Driver update(Driver driver) {
        return null;
    }

    public boolean delete(Integer id) {
        String sql = "DELETE FROM drivers WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            int result = statement.executeUpdate();
            statement.close();

            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}

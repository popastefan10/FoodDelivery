package repositories;

import db.DatabaseConnection;
import models.Driver;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class DriverRepository extends GenericRepository<Driver> {
    private static DriverRepository instance = null;

    private DriverRepository() {
        connection = DatabaseConnection.getInstance();
    }

    private PreparedStatement populateStatement(PreparedStatement statement, Driver driver) throws SQLException {
        statement.setString(1, driver.getEmail());
        statement.setString(2, driver.getPhoneNumber());
        statement.setString(3, driver.getFirstName());
        statement.setString(4, driver.getLastName());
        statement.setFloat(5, driver.getRating());

        return statement;
    }

    public static DriverRepository getInstance() {
        if (instance == null)
            instance = new DriverRepository();
        return instance;
    }

    @Override
    protected String getTableName() {
        return "drivers";
    }

    public Driver create(Driver driver) {
        String sql = "INSERT INTO drivers (id, email, \"phone-number\", \"first-name\", \"last-name\", rating) " +
                "VALUES" +
                "(DEFAULT, ?, ?, ?, ?, ?)";
        try {
            var statement = populateStatement(connection.prepareStatement(sql), driver);
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
        String sql = "UPDATE drivers SET email = ?, \"phone-number\" = ?, \"first-name\" = ?, \"last-name\" = ?, " +
                "rating = ? WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, driver.getEmail());
            statement.setString(2, driver.getPhoneNumber());
            statement.setString(3, driver.getFirstName());
            statement.setString(4, driver.getLastName());
            statement.setFloat(5, driver.getRating());
            statement.setInt(6, driver.getId());
            int result = statement.executeUpdate();
            statement.close();

            return result > 0 ? driver : null;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}

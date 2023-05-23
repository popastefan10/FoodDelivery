package repositories;

import db.DatabaseConnection;
import models.Restaurant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class RestaurantRepository implements GenericRepository<Restaurant> {
    private static RestaurantRepository instance = null;
    private final Connection connection;

    private RestaurantRepository() {
        connection = DatabaseConnection.getInstance();
    }

    public static RestaurantRepository getInstance() {
        if (instance == null)
            instance = new RestaurantRepository();
        return instance;
    }

    public Restaurant create(Restaurant restaurant) {
        String sql = "INSERT INTO restaurants (id, email, \"phone-number\", name, address, rating) " +
                "VALUES" +
                "(DEFAULT, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, restaurant.getEmail());
            statement.setString(2, restaurant.getPhoneNumber());
            statement.setString(3, restaurant.getName());
            statement.setString(4, restaurant.getAddress());
            statement.setFloat(5, restaurant.getRating());
            statement.executeUpdate();
            statement.close();

            return restaurant;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Map<Integer, Restaurant> getAll() {
        var restaurants = new HashMap<Integer, Restaurant>();
        String sql = "SELECT * FROM restaurants";

        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                var restaurant = new Restaurant(result.getString(2), result.getString(3),
                                                result.getString(4), result.getString(5),
                                                result.getFloat(6));
                restaurant.setId(result.getInt(1));
                restaurants.put(restaurant.getId(), restaurant);
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return restaurants;
    }

    public Restaurant getById(Integer id) {
        String sql = "SELECT * FROM restaurants WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            ResultSet result = statement.executeQuery();
            if (result.next()) {
                var restaurant = new Restaurant(result.getString(2), result.getString(3),
                                                result.getString(4), result.getString(5),
                                                result.getFloat(6));
                restaurant.setId(result.getInt(1));
                statement.close();

                return restaurant;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public Restaurant update(Restaurant restaurant) {
        String sql = "UPDATE restaurants SET email = ?, \"phone-number\" = ?, name = ?, address = ?, rating = ? WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, restaurant.getEmail());
            statement.setString(2, restaurant.getPhoneNumber());
            statement.setString(3, restaurant.getName());
            statement.setString(4, restaurant.getAddress());
            statement.setFloat(5, restaurant.getRating());
            statement.setInt(6, restaurant.getId());
            int result = statement.executeUpdate();
            statement.close();

            return result > 0 ? restaurant : null;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean delete(Integer id) {
        String sql = "DELETE FROM restaurants WHERE id = ?";
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

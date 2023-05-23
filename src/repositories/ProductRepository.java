package repositories;

import db.DatabaseConnection;
import models.Product;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class ProductRepository extends GenericRepository<Product> {
    private static ProductRepository instance = null;

    private ProductRepository() {
        connection = DatabaseConnection.getInstance();
    }

    private HashMap<Integer, Product> parseProducts(ResultSet result) throws SQLException {
        var products = new HashMap<Integer, Product>();
        while (result.next()) {
            var product = new Product(result.getInt(2), result.getString(3), result.getFloat(4),
                                      result.getString(5), result.getFloat(6));
            product.setId(result.getInt(1));
            products.put(product.getId(), product);
        }

        return products;
    }

    public static ProductRepository getInstance() {
        if (instance == null)
            instance = new ProductRepository();
        return instance;
    }

    @Override
    protected String getTableName() {
        return "products";
    }

    public Product create(Product product) {
        String sql = "INSERT INTO products (id, \"restaurant-id\", \"name\", quantity, \"measurement-unit\", price) " +
                "VALUES" +
                " (DEFAULT, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, product.getRestaurantId());
            statement.setString(2, product.getName());
            statement.setFloat(3, product.getQuantity());
            statement.setString(4, product.getMeasurementUnit());
            statement.setFloat(5, product.getPrice());
            statement.executeUpdate();
            statement.close();

            return product;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Map<Integer, Product> getAll() {
        Map<Integer, Product> products = null;
        String sql = "SELECT * FROM products";

        try {
            Statement statement = connection.createStatement();
            products = parseProducts(statement.executeQuery(sql));
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return products;
    }

    public Map<Integer, Product> getAllByRestaurantId(Integer restaurantId) {
        var products = new HashMap<Integer, Product>();
        String sql = "SELECT * FROM products WHERE \"restaurant-id\" = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, restaurantId);
            products = parseProducts(statement.executeQuery());
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return products;
    }

    public Product getById(Integer id) {
        String sql = "SELECT * FROM products WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            ResultSet result = statement.executeQuery();
            if (result.next()) {
                var product = new Product(result.getInt(2), result.getString(3), result.getFloat(4),
                                          result.getString(5), result.getFloat(6));
                product.setId(result.getInt(1));
                statement.close();

                return product;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public Product update(Product product) {
        String sql = "UPDATE products SET name = ?, quantity = ?, \"measurement-unit\" = ?, price = ? WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, product.getName());
            statement.setFloat(2, product.getQuantity());
            statement.setString(3, product.getMeasurementUnit());
            statement.setFloat(4, product.getPrice());
            statement.setInt(5, product.getId());
            int result = statement.executeUpdate();
            statement.close();

            return result > 0 ? product : null;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}

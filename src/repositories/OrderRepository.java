package repositories;

import db.DatabaseConnection;
import models.Order;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class OrderRepository implements GenericRepository<Order> {
    private static OrderRepository instance = null;
    private final Connection connection;
    private final OrderItemRepository orderItemRepository = OrderItemRepository.getInstance();

    private OrderRepository() {
        connection = DatabaseConnection.getInstance();
    }

    private Order parseOne(ResultSet result) throws SQLException {
        var order = new Order(result.getInt(2), result.getInt(3),
                              result.getInt(4), Order.OrderStatus.valueOf(result.getString(5)));
        order.setId(result.getInt(1));

        return order;
    }

    private HashMap<Integer, Order> parseAll(ResultSet result) throws SQLException {
        var orders = new HashMap<Integer, Order>();
        while (result.next()) {
            var order = parseOne(result);
            order.setItems(orderItemRepository.getAllByOrderId(order.getId()));
            orders.put(order.getId(), order);
        }
        return orders;
    }

    public static OrderRepository getInstance() {
        if (instance == null)
            instance = new OrderRepository();
        return instance;
    }

    public Order create(Order order) {
        String sql = "INSERT INTO orders (id, \"customer-id\", \"restaurant-id\", \"driver-id\", status) " +
                "VALUES" +
                "(DEFAULT, ?, ?, ?, CAST(? AS \"order-status\")) RETURNING id";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, order.getCustomerId());
            statement.setInt(2, order.getRestaurantId());
            statement.setInt(3, order.getDriverId());
            statement.setString(4, order.getStatus().name());
            ResultSet result = statement.executeQuery();
            if (!result.next())
                return null;
            order.setId(result.getInt(1));
            statement.close();

            return order;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Map<Integer, Order> getAll() {
        String sql = "SELECT * FROM orders";

        try {
            Statement statement = connection.createStatement();
            var orders = parseAll(statement.executeQuery(sql));
            statement.close();

            return orders;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public Order getById(Integer id) {
        String sql = "SELECT * FROM orders WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            ResultSet result = statement.executeQuery();
            if (result.next()) {
                var order = parseOne(result);
                order.setItems(orderItemRepository.getAllByOrderId(order.getId()));
                statement.close();

                return order;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public Order update(Order order) {
        String sql = "UPDATE orders SET \"driver-id\" = ?, status = ? WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, order.getDriverId());
            statement.setString(2, order.getStatus().name());
            statement.setInt(3, order.getId());
            int result = statement.executeUpdate();
            statement.close();

            return result > 0 ? order : null;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean delete(Integer id) {
        String sql = "DELETE FROM orders WHERE id = ?";
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

package repositories;

import db.DatabaseConnection;
import models.OrderItem;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class OrderItemRepository extends GenericRepository<OrderItem> {
    private static OrderItemRepository instance = null;

    private OrderItemRepository() {
        connection = DatabaseConnection.getInstance();
    }

    private OrderItem parseOne(ResultSet result) throws SQLException {
        var item = new OrderItem(result.getInt(2), result.getInt(3),
                                 result.getInt(4));
        item.setId(result.getInt(1));
        return item;
    }

    private HashMap<Integer, OrderItem> parseAll(ResultSet result) throws SQLException {
        var items = new HashMap<Integer, OrderItem>();
        while (result.next()) {
            var item = parseOne(result);
            items.put(item.getId(), item);
        }
        return items;
    }

    public static OrderItemRepository getInstance() {
        if (instance == null)
            instance = new OrderItemRepository();
        return instance;
    }

    @Override
    protected String getTableName() {
        return "\"order-items\"";
    }

    public OrderItem create(OrderItem item) {
        String sql = "INSERT INTO \"order-items\" (id, \"order-id\", \"product-id\", quantity)" +
                "VALUES" +
                "(DEFAULT, ?, ?, ?) RETURNING id";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, item.getOrderId());
            statement.setInt(2, item.getProductId());
            statement.setInt(3, item.getQuantity());
            ResultSet result = statement.executeQuery();
            if (!result.next())
                return null;
            item.setId(result.getInt(1));
            statement.close();

            return item;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Map<Integer, OrderItem> getAll() {
        return null;
    }

    public Map<Integer, OrderItem> getAllByOrderId(Integer orderId) {
        String sql = "SELECT * FROM \"order-items\" WHERE \"order-id\" = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, orderId);
            ResultSet result = statement.executeQuery();
            var items = parseAll(result);
            statement.close();

            return items;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public OrderItem getById(Integer id) {
        String sql = "SELECT * FROM \"order-items\" WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            ResultSet result = statement.executeQuery();
            if (result.next()) {
                var item = parseOne(result);
                statement.close();

                return item;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public OrderItem update(OrderItem item) {
        String sql = "UPDATE \"order-items\" SET quantity = ? WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, item.getQuantity());
            statement.setInt(2, item.getId());
            int result = statement.executeUpdate();
            statement.close();

            return result > 0 ? item : null;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}

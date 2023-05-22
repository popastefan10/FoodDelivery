package repositories;

import db.DatabaseConnection;
import models.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class CustomerRepository implements GenericRepository<Customer> {
    private static CustomerRepository instance = null;
    private final Connection connection;

    private CustomerRepository() {
        connection = DatabaseConnection.getInstance();
    }

    public static CustomerRepository getInstance() {
        if (instance == null)
            instance = new CustomerRepository();
        return instance;
    }

    public Customer create(Customer customer) {
        String sql = "INSERT INTO customers (id, email, \"phone-number\", \"first-name\", \"last-name\", address) " +
                "VALUES" +
                "(DEFAULT, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, customer.getEmail());
            statement.setString(2, customer.getPhoneNumber());
            statement.setString(3, customer.getFirstName());
            statement.setString(4, customer.getLastName());
            statement.setString(5, customer.getAddress());
            statement.executeUpdate();
            statement.close();

            return customer;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

    public Map<Integer, Customer> getAll() {
        var customers = new HashMap<Integer, Customer>();
        String sql = "SELECT * FROM customers";

        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                var customer = new Customer(result.getString(2), result.getString(3),
                                            result.getString(4), result.getString(5),
                                            result.getString(6));
                customer.setId(result.getInt(1));
                customers.put(customer.getId(), customer);
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return customers;
    }

    public Customer getById(Integer id) {
        String sql = "SELECT * FROM customers WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            ResultSet result = statement.executeQuery();
            if (result.next()) {
                var customer = new Customer(result.getString(2), result.getString(3),
                                            result.getString(4), result.getString(5),
                                            result.getString(6));
                customer.setId(result.getInt(1));
                statement.close();

                return customer;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public Customer update(Customer customer) {
        String sql = "UPDATE customers SET email = ?, \"phone-number\" = ?, \"first-name\" = ?, \"last-name\" = ?, address = ? WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, customer.getEmail());
            statement.setString(2, customer.getPhoneNumber());
            statement.setString(3, customer.getFirstName());
            statement.setString(4, customer.getLastName());
            statement.setString(5, customer.getAddress());
            statement.setInt(6, customer.getId());
            int result = statement.executeUpdate();
            statement.close();

            return result > 0 ? customer : null;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean delete(Integer id) {
        String sql = "DELETE FROM customers WHERE id = ?";
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

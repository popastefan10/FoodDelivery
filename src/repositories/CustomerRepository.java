package repositories;

import db.DatabaseConnection;
import models.Customer;

import java.sql.Connection;

public class CustomerRepository {
    private static CustomerRepository instance = null;
    private final Connection connection;

    private CustomerRepository() {
        connection = DatabaseConnection.getConnection();
    }

    public static CustomerRepository getInstance() {
        if (instance == null)
            instance = new CustomerRepository();
        return instance;
    }

    public Customer create(Customer customer) {
        String sql = "INSERT INTO customers (id, email, phonenumber, firstName, lastName, address) VALUES" +
                "(DEFAULT, ?, ?, ?, ?, ?)";
        try {
            var statement = connection.prepareStatement(sql);
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
}

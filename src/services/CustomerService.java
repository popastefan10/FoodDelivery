package services;

import models.Customer;
import utils.IOUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;

public class CustomerService extends PersonService {
    Map<UUID, Customer> customersMap = new HashMap<UUID, Customer>();

    public Customer readCustomer(Scanner in) {
        System.out.println("Creating a new customer...");
        Customer customer = new Customer();

        super.readPerson(in, customer);

        customer.setAddress(IOUtils.readString(in, "Address: ", 1));

        return customer;
    }

    public void printCustomer(Customer customer) {
        System.out.println("Customer with id " + customer.getId() + ":");
        super.printPerson(customer);
        System.out.println("\tAddress: " + customer.getAddress());
    }

    public void addCustomer(Customer customer) {
        customersMap.put(customer.getId(), customer);
    }

    public Customer[] getCustomers() {
        return customersMap.values().toArray(new Customer[0]);
    }
}

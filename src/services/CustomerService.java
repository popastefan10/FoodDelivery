package services;

import models.Customer;
import utils.IOUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;

public class CustomerService {
    private static final Map<UUID, Customer> customersMap = new HashMap<UUID, Customer>();

    public static Customer readCustomer(Scanner in) {
        System.out.println("Creating a new customer...");
        Customer customer = new Customer();

        PersonService.readPerson(in, customer);

        customer.setAddress(IOUtils.readString(in, "Address: ", 1));

        return customer;
    }

    public static void printCustomer(Customer customer) {
        System.out.println("Customer with id " + customer.getId() + ":");
        PersonService.printPerson(customer);
        System.out.println("\tAddress: " + customer.getAddress());
    }

    public static void printCustomerShort(Customer customer) {
        System.out.printf("\t%s\t%s %s\n", customer.getId().toString(), customer.getFirstName(),
                          customer.getLastName());
    }

    public static void addCustomer(Customer customer) {
        customersMap.put(customer.getId(), customer);
    }

    public static Customer[] getCustomers() {
        return customersMap.values().toArray(new Customer[0]);
    }

    public static Customer getCustomerById(UUID customerId) {
        return customersMap.get(customerId);
    }
}

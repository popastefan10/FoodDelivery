package services;

import models.Customer;
import utils.IOUtils;

import java.util.Scanner;

public class CustomerService extends PersonService {
    public Customer createCustomer(Scanner in) {
        System.out.println("Creating a new customer...");
        Customer customer = new Customer();

        super.createPerson(in, customer);

        customer.setAddress(IOUtils.readString(in, "Address: ", 1));

        return customer;
    }

    public void printCustomer(Customer customer) {
        System.out.println("Customer with id " + customer.getId() + ":");
        super.printPerson(customer);
        System.out.println("\tAddress: " + customer.getAddress());
    }
}

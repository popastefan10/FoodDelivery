package services;

import models.Customer;
import repositories.CustomerRepository;
import utils.IOUtils;

import java.util.Scanner;

public class CustomerService {
    private static final CustomerRepository customerRepository = CustomerRepository.getInstance();

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
        System.out.printf("\t%s\t%s %s\n", customer.getId(), customer.getFirstName(),
                          customer.getLastName());
    }

    public static Customer create(Customer customer) {
        return customerRepository.create(customer);
    }

    public static Customer[] getAll() {
        return customerRepository.getAll().values().toArray(new Customer[0]);
    }

    public static Customer getById(Integer id) {
        return customerRepository.getById(id);
    }

    public static boolean delete(Integer id) {
        return customerRepository.delete(id);
    }
}

package services;

import models.Customer;
import repositories.CustomerRepository;
import utils.IOUtils;

import java.util.Map;
import java.util.Scanner;

public class CustomerService {
    private static final CustomerRepository customerRepository = CustomerRepository.getInstance();
    private static final AuditService auditService = AuditService.getInstance();

    public static Customer readCustomer(Scanner in) {
        System.out.println("Creating a new customer...");
        Customer customer = new Customer();

        PersonService.readPerson(in, customer);
        customer.setAddress(IOUtils.readString(in, "Address: ", 1));

        return customer;
    }

    public static Customer readCustomer(Scanner in, Customer defaultValue) {
        System.out.println("Updating customer...");
        Customer customer = new Customer();
        customer.setId(defaultValue.getId());

        PersonService.readPerson(in, customer, defaultValue);
        customer.setAddress(IOUtils.readString(in, "Address", defaultValue.getAddress(), 1));

        return customer;
    }

    public static Integer readCustomerId(Scanner in) {
        Map<Integer, Customer> customers = customerRepository.getAll();
        System.out.println("Here are all available customers:");
        for (Customer customer : customers.values())
            printCustomerShort(customer);
        System.out.println();

        while (true) {
            Integer customerId = IOUtils.readInt(in, "Choose a customer id: ", 0);
            if (customers.containsKey(customerId))
                return customerId;
            System.out.println(IOUtils.ANSI_RED + "There is no customer with this id!" + IOUtils.ANSI_RESET);
        }
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
        auditService.logAction("customer_create");
        return customerRepository.create(customer);
    }

    public static Customer[] getAll() {
        auditService.logAction("customer_get_all");
        return customerRepository.getAll().values().toArray(new Customer[0]);
    }

    public static Customer getById(Integer id) {
        auditService.logAction("customer_get_by_id");
        return customerRepository.getById(id);
    }

    public static Customer update(Customer customer) {
        auditService.logAction("customer_update");
        return customerRepository.update(customer);
    }

    public static boolean delete(Integer id) {
        auditService.logAction("customer_delete");
        return customerRepository.delete(id);
    }
}

package services;

import models.Customer;
import repositories.CustomerRepository;
import utils.IOUtils;

import java.util.Map;
import java.util.Scanner;

public class CustomerService {
    private static CustomerService instance = null;
    private final CustomerRepository customerRepository = CustomerRepository.getInstance();
    private final AuditService auditService = AuditService.getInstance();
    private final PersonService personService = PersonService.getInstance();

    private CustomerService() {
    }

    public static CustomerService getInstance() {
        if (instance == null)
            instance = new CustomerService();
        return instance;
    }

    public Customer readCustomer(Scanner in) {
        System.out.println("Creating a new customer...");
        Customer customer = new Customer();

        personService.readPerson(in, customer);
        customer.setAddress(IOUtils.readString(in, "Address: ", 1));

        return customer;
    }

    public Customer readCustomer(Scanner in, Customer defaultValue) {
        System.out.println("Updating customer...");
        Customer customer = new Customer();
        customer.setId(defaultValue.getId());

        personService.readPerson(in, customer, defaultValue);
        customer.setAddress(IOUtils.readString(in, "Address", defaultValue.getAddress(), 1));

        return customer;
    }

    public Integer readCustomerId(Scanner in) {
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

    public void printCustomer(Customer customer) {
        System.out.println("Customer with id " + customer.getId() + ":");
        personService.printPerson(customer);
        System.out.println("\tAddress: " + customer.getAddress());
    }

    public void printCustomerShort(Customer customer) {
        System.out.printf("\t%s\t%s %s\n", customer.getId(), customer.getFirstName(),
                          customer.getLastName());
    }

    public Customer create(Customer customer) {
        auditService.logAction("customer_create");
        return customerRepository.create(customer);
    }

    public Customer[] getAll() {
        auditService.logAction("customer_get_all");
        return customerRepository.getAll().values().toArray(new Customer[0]);
    }

    public Customer getById(Integer id) {
        auditService.logAction("customer_get_by_id");
        return customerRepository.getById(id);
    }

    public Customer update(Customer customer) {
        auditService.logAction("customer_update");
        return customerRepository.update(customer);
    }

    public boolean delete(Integer id) {
        auditService.logAction("customer_delete");
        return customerRepository.delete(id);
    }
}

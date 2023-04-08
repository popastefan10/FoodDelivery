import menu.Menu;
import models.Customer;
import models.Driver;
import models.Product;
import models.Restaurant;
import services.CustomerService;
import services.DriverService;
import services.ProductService;
import services.RestaurantService;
import utils.IOUtils;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        CustomerService customerService = new CustomerService();
        DriverService driverService = new DriverService();
        RestaurantService restaurantService = new RestaurantService();

        Menu mainMenu = new Menu("Welcome to the food delivery app!");
        Menu databaseAdminMenu = new Menu("Here you can manage database resources.");
        Menu customersMenu = new Menu("Here you can manage customers.");
        Menu driversMenu = new Menu("Here you can manage drivers.");
        Menu restaurantsMenu = new Menu("Here you can manage restaurants.");
        Menu productsMenu = new Menu("Here you can manage products.");

        mainMenu.addOption("Manage database", () -> databaseAdminMenu.run(in));

        databaseAdminMenu.addOption("Manage customers", () -> customersMenu.run(in));
        databaseAdminMenu.addOption("Manage drivers", () -> driversMenu.run(in));
        databaseAdminMenu.addOption("Manage restaurants", () -> restaurantsMenu.run(in));
        databaseAdminMenu.addOption("Manage products", () -> productsMenu.run(in));

        customersMenu.addOption("Create a new customer", () -> {
            Customer customer = customerService.readCustomer(in);
            customerService.addCustomer(customer);
        });
        customersMenu.addOption("List all customers", () -> {
            Customer[] customers = customerService.getCustomers();
            if (customers.length == 0)
                IOUtils.printError("There are no customers in the database! Try creating one first.\n");
            else
                Arrays.stream(customers).forEach(CustomerService::printCustomerShort);
        });
        customersMenu.addOption("Get customer by id", () -> {
            Customer customer = CustomerService.getCustomerById(IOUtils.readUUID(in, "Enter customer id: ", 0));
            if (customer == null)
                IOUtils.printError("There is no customer with this id!");
            else
                CustomerService.printCustomer(customer);
        });

        driversMenu.addOption("Create a new driver", () -> {
            Driver driver = driverService.readDriver(in);
            driverService.addDriver(driver);
            System.out.println("Driver added successfully!");
        });
        driversMenu.addOption("List all drivers", () -> {
            Driver[] drivers = driverService.getDrivers();
            if (drivers.length == 0)
                IOUtils.printError("There are no drivers in the database! Try creating one first.\n");
            for (Driver customer : drivers) {
                System.out.println();
                driverService.printDriver(customer);
            }
        });
        driversMenu.addOption("Get driver by id", () -> {
            Driver driver = DriverService.getDriverById(IOUtils.readUUID(in, "Enter driver id: ", 0));
            if (driver == null)
                IOUtils.printError("There is no driver with this id!");
            else
                DriverService.printDriver(driver);
        });

        restaurantsMenu.addOption("Create a new restaurant", () -> {
            Restaurant restaurant = restaurantService.readRestaurant(in);
            restaurantService.addRestaurant(restaurant);
            System.out.println("Restaurant added successfully!");
        });
        restaurantsMenu.addOption("List all restaurants", () -> {
            Restaurant[] restaurants = RestaurantService.getRestaurants();
            if (restaurants.length == 0)
                IOUtils.printError("There are no restaurants in the database! Try creating one first.\n");
            else
                Arrays.stream(restaurants).forEach(RestaurantService::printRestaurantShort);
        });

        productsMenu.addOption("Create a new product", () -> {
            Product product = ProductService.readProduct(in);
            ProductService.addProduct(product);
            System.out.println("Product added successfully!");
        });
        productsMenu.addOption("List all products", () -> {
            Product[] products = ProductService.getProducts();
            if (products.length == 0)
                IOUtils.printError("There are no products in the database! Try creating one first.");
            else
                Arrays.stream(products).forEach(ProductService::printProductShort);
        });

        mainMenu.run(in);
    }
}
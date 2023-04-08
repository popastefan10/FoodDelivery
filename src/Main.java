import menu.Menu;
import models.Customer;
import models.Driver;
import models.Restaurant;
import services.CustomerService;
import services.DriverService;
import services.RestaurantService;
import utils.IOUtils;

import java.io.IOException;
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

        mainMenu.addOption("Manage database", () -> {
            databaseAdminMenu.run(in);
        });

        databaseAdminMenu.addOption("Manage customers", () -> {
            customersMenu.run(in);
        });
        databaseAdminMenu.addOption("Manage drivers", () -> {
            driversMenu.run(in);
        });
        databaseAdminMenu.addOption("Manage restaurants", () -> {
            restaurantsMenu.run(in);
        });

        customersMenu.addOption("Create a new customer", () -> {
            Customer customer = customerService.readCustomer(in);
            customerService.addCustomer(customer);
            System.out.println("Customer added succesfully!");
        });
        customersMenu.addOption("List all customers", () -> {
            Customer[] customers = customerService.getCustomers();
            if (customers.length == 0)
                System.out.println("There are no customers in the database! Try creating one first.\n");
            for (Customer customer : customers) {
                System.out.println();
                customerService.printCustomer(customer);
            }
        });

        driversMenu.addOption("Create a new driver", () -> {
            Driver driver = driverService.readDriver(in);
            driverService.addDriver(driver);
            System.out.println("Driver added succesfully!");
        });
        driversMenu.addOption("List all drivers", () -> {
            Driver[] drivers = driverService.getDrivers();
            if (drivers.length == 0)
                System.out.println("There are no drivers in the database! Try creating one first.\n");
            for (Driver customer : drivers) {
                System.out.println();
                driverService.printDriver(customer);
            }
        });

        restaurantsMenu.addOption("Create a new restaurant", () -> {
            Restaurant restaurant = restaurantService.readRestaurant(in);
            restaurantService.addRestaurant(restaurant);
            System.out.println("Restaurant added succesfully!");
        });
        restaurantsMenu.addOption("List all restaurants", () -> {
            Restaurant[] restaurants = restaurantService.getRestaurants();
            if (restaurants.length == 0)
                System.out.println("There are no restaurants in the database! Try creating one first.\n");
            for (Restaurant restaurant : restaurants) {
                System.out.println();
                restaurantService.printRestaurant(restaurant);
            }
        });

        mainMenu.run(in);
    }
}
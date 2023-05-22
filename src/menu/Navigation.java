package menu;

import models.Customer;
import models.Driver;
import models.Product;
import models.Restaurant;
import services.CustomerService;
import services.DriverService;
import services.ProductService;
import services.RestaurantService;
import utils.IOUtils;

import java.util.*;

public class Navigation {
    private static Navigation instance = null;
    private final Scanner in;

    private static enum MenuID {
        MAIN,
        CUSTOMERS,
        DRIVERS,
        RESTAURANTS,
        ORDERS,
        PRODUCTS,
        BACK,
        EXIT
    }
    private final Map<MenuID, Menu> menus = new HashMap<MenuID, Menu>();
    private final List<Menu> menusStack = new ArrayList<Menu>();

    private Navigation(Scanner in) {
        this.in = in;
    }

    public static Navigation getInstance(Scanner in) {
        if (instance == null)
            instance = new Navigation(in);
        return instance;
    }

    private void navigate(MenuID menuID) {
        switch (menuID) {
            case BACK -> menusStack.remove(menusStack.size() - 1);
            case EXIT -> menusStack.clear();
            default -> {
                Menu menu = menus.get(menuID);
                menusStack.add(menu);
            }
        }
    }

    public void registerMainMenu() {
        Menu mainMenu = new Menu("Welcome to the food delivery app!");

        mainMenu.addOption("Manage customers", () -> navigate(MenuID.CUSTOMERS));
        mainMenu.addOption("Manage orders", () -> navigate(MenuID.ORDERS));
        mainMenu.addOption("Manage products", () -> navigate(MenuID.PRODUCTS));
        mainMenu.addOption("Exit", () -> navigate(MenuID.EXIT));

        menus.put(MenuID.MAIN, mainMenu);
    }

    public void registerCustomersMenu() {
        Menu customersMenu = new Menu("Customers");

        customersMenu.addOption("Create a new customer", () -> {
            Customer customer = CustomerService.create(CustomerService.readCustomer(in));
            System.out.println("Customer created successfully! Customer's id is: " + customer.getId());
        });
        customersMenu.addOption("List all customers", () -> {
            Customer[] customers = CustomerService.getAll();
            if (customers.length == 0)
                IOUtils.printError("There are no customers in the database! Try creating one first.\n");
            else
                Arrays.stream(customers).forEach(CustomerService::printCustomerShort);
        });
        customersMenu.addOption("Get customer", () -> {
            Customer customer = CustomerService.getById(IOUtils.readInt(in, "Enter customer id: ", 0));
            if (customer == null)
                IOUtils.printError("There is no customer with this id!");
            else
                CustomerService.printCustomer(customer);
        });
        customersMenu.addOption("Delete customer", () -> {
            int id = IOUtils.readInt(in, "Enter customer id: ", 0);
            boolean result = CustomerService.delete(id);
            if (result)
                System.out.println("Customer deleted successfully!");
            else
                IOUtils.printError("There is no customer with this id!");
        });
        customersMenu.addOption("Back", () -> navigate(MenuID.BACK));

        menus.put(MenuID.CUSTOMERS, customersMenu);
    }

    public void registerDriversMenu() {
        Menu driversMenu = new Menu("Drivers");

        driversMenu.addOption("Create a new driver", () -> {
            Driver driver = DriverService.readDriver(in);
            DriverService.addDriver(driver);
            System.out.println("Driver added successfully!");
        });
        driversMenu.addOption("List all drivers", () -> {
            Driver[] drivers = DriverService.getDrivers();
            if (drivers.length == 0)
                IOUtils.printError("There are no drivers in the database! Try creating one first.\n");
            for (Driver customer : drivers) {
                System.out.println();
                DriverService.printDriver(customer);
            }
        });
        driversMenu.addOption("Get driver by id", () -> {
            Driver driver = DriverService.getDriverById(IOUtils.readInt(in, "Enter driver id: ", 0));
            if (driver == null)
                IOUtils.printError("There is no driver with this id!");
            else
                DriverService.printDriver(driver);
        });
        driversMenu.addOption("Back", () -> navigate(MenuID.BACK));

        menus.put(MenuID.DRIVERS, driversMenu);
    }

    public void registerRestaurantsMenu() {
        RestaurantService restaurantService = new RestaurantService();
        Menu restaurantsMenu = new Menu("Restaurants");

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
        restaurantsMenu.addOption("Back", () -> navigate(MenuID.BACK));

        menus.put(MenuID.RESTAURANTS, restaurantsMenu);
    }

    public void registerProductsMenu() {
        Menu productsMenu = new Menu("Products");

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
        productsMenu.addOption("Back", () -> navigate(MenuID.BACK));

        menus.put(MenuID.PRODUCTS, productsMenu);
    }

    public void registerDefaultMenus() {
        registerMainMenu();
        registerCustomersMenu();
        registerDriversMenu();
        registerRestaurantsMenu();
        registerProductsMenu();
    }

    public void run() {
        menusStack.add(menus.get(MenuID.MAIN));
        while (!menusStack.isEmpty()) {
            Menu menu = menusStack.get(menusStack.size() - 1);
            menu.run(in);
        }
    }
}

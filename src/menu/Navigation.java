package menu;

import models.*;
import services.CustomerService;
import services.DriverService;
import services.ProductService;
import services.RestaurantService;
import services.OrderService;
import utils.IOUtils;

import java.util.*;

public class Navigation {
    private static Navigation instance = null;
    private final Scanner in;

    private enum MenuID {
        MAIN,
        CUSTOMERS,
        DRIVERS,
        RESTAURANTS,
        ORDERS,
        PRODUCTS,
        BACK,
        EXIT
    }

    private final Map<MenuID, Menu> menus = new HashMap<>();
    private final List<Menu> menusStack = new ArrayList<>();

    private Navigation(Scanner in) {
        this.in = in;
    }

    public static Navigation getInstance(Scanner in) {
        if (instance == null)
            instance = new Navigation(in);
        return instance;
    }

    private void navigate(MenuID menuID) throws IllegalArgumentException {
        switch (menuID) {
            case BACK -> menusStack.remove(menusStack.size() - 1);
            case EXIT -> menusStack.clear();
            default -> {
                Menu menu = menus.get(menuID);
                if (menu == null)
                    IOUtils.printError("Menu not found!");
                else
                    menusStack.add(menu);
            }
        }
    }

    public void registerMainMenu() {
        Menu mainMenu = new Menu("Welcome to the food delivery app!");

        mainMenu.addOption("Manage customers", () -> navigate(MenuID.CUSTOMERS));
        mainMenu.addOption("Manage drivers", () -> navigate(MenuID.DRIVERS));
        mainMenu.addOption("Manage restaurants", () -> navigate(MenuID.RESTAURANTS));
        mainMenu.addOption("Manage products", () -> navigate(MenuID.PRODUCTS));
        mainMenu.addOption("Manage orders", () -> navigate(MenuID.ORDERS));
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
        customersMenu.addOption("Update customer", () -> {
            int id = IOUtils.readInt(in, "Enter customer id: ", 0);
            Customer customer = CustomerService.getById(id);
            if (customer == null)
                IOUtils.printError("There is no customer with this id!");
            else {
                Customer updatedCustomer = CustomerService.update(CustomerService.readCustomer(in, customer));
                if (updatedCustomer == null)
                    IOUtils.printError("Update failed!");
                else
                    System.out.println("Customer updated successfully!");
            }
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
            DriverService.create(driver);
            System.out.println("Driver added successfully!");
        });
        driversMenu.addOption("List all drivers", () -> {
            Driver[] drivers = DriverService.getAll();
            if (drivers.length == 0)
                IOUtils.printError("There are no drivers in the database! Try creating one first.\n");
            else
                Arrays.stream(drivers).forEach(DriverService::printDriverShort);
        });
        driversMenu.addOption("Get driver", () -> {
            Driver driver = DriverService.getById(IOUtils.readInt(in, "Enter driver id: ", 0));
            if (driver == null)
                IOUtils.printError("There is no driver with this id!");
            else
                DriverService.printDriver(driver);
        });
        driversMenu.addOption("Update driver", () -> {
            int id = IOUtils.readInt(in, "Enter driver id: ", 0);
            Driver driver = DriverService.getById(id);
            if (driver == null)
                IOUtils.printError("There is no customer with this id!");
            else {
                Driver updatedDriver = DriverService.update(DriverService.readDriver(in, driver));
                if (updatedDriver == null)
                    IOUtils.printError("Update failed!");
                else
                    System.out.println("Driver updated successfully!");
            }
        });
        driversMenu.addOption("Delete driver", () -> {
            int id = IOUtils.readInt(in, "Enter driver id: ", 0);
            boolean result = DriverService.delete(id);
            if (result)
                System.out.println("Driver deleted successfully!");
            else
                IOUtils.printError("There is no driver with this id!");
        });
        driversMenu.addOption("Back", () -> navigate(MenuID.BACK));

        menus.put(MenuID.DRIVERS, driversMenu);
    }

    public void registerRestaurantsMenu() {
        Menu restaurantsMenu = new Menu("Restaurants");

        restaurantsMenu.addOption("Create a new restaurant", () -> {
            Restaurant restaurant = RestaurantService.readRestaurant(in);
            RestaurantService.create(restaurant);
            System.out.println("Restaurant added successfully!");
        });
        restaurantsMenu.addOption("List all restaurants", () -> {
            Restaurant[] restaurants = RestaurantService.getAll();
            if (restaurants.length == 0)
                IOUtils.printError("There are no restaurants in the database! Try creating one first.\n");
            else
                Arrays.stream(restaurants).forEach(RestaurantService::printRestaurantShort);
        });
        restaurantsMenu.addOption("Get restaurant", () -> {
            Restaurant restaurant = RestaurantService.getById(IOUtils.readInt(in, "Enter restaurant id: ", 0));
            if (restaurant == null)
                IOUtils.printError("There is no restaurant with this id!");
            else
                RestaurantService.printRestaurant(restaurant);
        });
        restaurantsMenu.addOption("Update restaurant", () -> {
            int id = IOUtils.readInt(in, "Enter restaurant id: ", 0);
            Restaurant restaurant = RestaurantService.getById(id);
            if (restaurant == null)
                IOUtils.printError("There is no restaurant with this id!");
            else {
                Restaurant updatedRestaurant = RestaurantService.update(
                        RestaurantService.readRestaurant(in, restaurant));
                if (updatedRestaurant == null)
                    IOUtils.printError("Update failed!");
                else
                    System.out.println("Restaurant updated successfully!");
            }
        });
        restaurantsMenu.addOption("Delete restaurant", () -> {
            int id = IOUtils.readInt(in, "Enter restaurant id: ", 0);
            boolean result = RestaurantService.delete(id);
            if (result)
                System.out.println("Restaurant deleted successfully!");
            else
                IOUtils.printError("There is no restaurant with this id!");
        });
        restaurantsMenu.addOption("Back", () -> navigate(MenuID.BACK));

        menus.put(MenuID.RESTAURANTS, restaurantsMenu);
    }

    public void registerProductsMenu() {
        Menu productsMenu = new Menu("Products");

        productsMenu.addOption("Create a new product", () -> {
            Product product = ProductService.readProduct(in);
            ProductService.create(product);
            System.out.println("Product added successfully!");
        });
        productsMenu.addOption("List all products", () -> {
            Product[] products = ProductService.getAll();
            if (products.length == 0)
                IOUtils.printError("There are no products in the database! Try creating one first.");
            else
                Arrays.stream(products).forEach(ProductService::printProductShort);
        });
        productsMenu.addOption("List all products from a restaurant", () -> {
            Integer restaurantId = RestaurantService.readRestaurantId(in);
            Product[] products = ProductService.getAllByRestaurantId(restaurantId);
            if (products.length == 0)
                IOUtils.printError("There are no products in the database! Try creating one first.");
            else
                Arrays.stream(products).forEach(ProductService::printProductShort);
        });
        productsMenu.addOption("Get product", () -> {
            Product product = ProductService.getById(IOUtils.readInt(in, "Enter product id: ", 0));
            if (product == null)
                IOUtils.printError("There is no product with this id!");
            else
                ProductService.printProduct(product);
        });
        productsMenu.addOption("Update product", () -> {
            int id = IOUtils.readInt(in, "Enter product id: ", 0);
            Product product = ProductService.getById(id);
            if (product == null)
                IOUtils.printError("There is no product with this id!");
            else {
                Product updatedProduct = ProductService.update(ProductService.readProduct(in, product));
                if (updatedProduct == null)
                    IOUtils.printError("Update failed!");
                else
                    System.out.println("Product updated successfully!");
            }
        });
        productsMenu.addOption("Delete product", () -> {
            int id = IOUtils.readInt(in, "Enter product id: ", 0);
            boolean result = ProductService.delete(id);
            if (result)
                System.out.println("Product deleted successfully!");
            else
                IOUtils.printError("There is no product with this id!");
        });
        productsMenu.addOption("Back", () -> navigate(MenuID.BACK));

        menus.put(MenuID.PRODUCTS, productsMenu);
    }

    public void registerOrdersMenu() {
        Menu ordersMenu = new Menu("Orders");

        ordersMenu.addOption("Create a new order", () -> {
            Order order = OrderService.readOrder(in);
            OrderService.create(order);
            System.out.println("Order added successfully!");
        });
        ordersMenu.addOption("List all orders", () -> {
            Order[] orders = OrderService.getAll();
            if (orders.length == 0)
                IOUtils.printError("There are no orders in the database! Try creating one first.");
            else
                Arrays.stream(orders).forEach(OrderService::printOrderShort);
        });
        ordersMenu.addOption("Get order", () -> {
            Order order = OrderService.getById(IOUtils.readInt(in, "Enter order id: ", 0));
            if (order == null)
                IOUtils.printError("There is no order with this id!");
            else
                OrderService.printOrder(order);
        });
        ordersMenu.addOption("Delete order", () -> {
            int id = IOUtils.readInt(in, "Enter order id: ", 0);
            boolean result = OrderService.delete(id);
            if (result)
                System.out.println("Order deleted successfully!");
            else
                IOUtils.printError("There is no order with this id!");
        });
        ordersMenu.addOption("Back", () -> navigate(MenuID.BACK));

        menus.put(MenuID.ORDERS, ordersMenu);
    }

    public void registerDefaultMenus() {
        registerMainMenu();
        registerCustomersMenu();
        registerDriversMenu();
        registerRestaurantsMenu();
        registerProductsMenu();
        registerOrdersMenu();
    }

    public void run() {
        menusStack.add(menus.get(MenuID.MAIN));
        while (!menusStack.isEmpty()) {
            Menu menu = menusStack.get(menusStack.size() - 1);
            menu.run(in);
        }
    }
}

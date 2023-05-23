package menu;

import models.*;
import services.*;
import utils.IOUtils;

import java.util.*;

public class Navigation {
    private static Navigation instance = null;
    private final CustomerService customerService = CustomerService.getInstance();
    private final DriverService driverService = DriverService.getInstance();
    private final OrderService orderService = OrderService.getInstance();
    private final ProductService productService = ProductService.getInstance();
    private final RestaurantService restaurantService = RestaurantService.getInstance();
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
            Customer customer = customerService.create(customerService.readCustomer(in));
            System.out.println("Customer created successfully! Customer's id is: " + customer.getId());
        });
        customersMenu.addOption("List all customers", () -> {
            Customer[] customers = customerService.getAll();
            if (customers.length == 0)
                IOUtils.printError("There are no customers in the database! Try creating one first.\n");
            else
                Arrays.stream(customers).forEach(customerService::printCustomerShort);
        });
        customersMenu.addOption("Get customer", () -> {
            Customer customer = customerService.getById(IOUtils.readInt(in, "Enter customer id: ", 0));
            if (customer == null)
                IOUtils.printError("There is no customer with this id!");
            else
                customerService.printCustomer(customer);
        });
        customersMenu.addOption("Update customer", () -> {
            int id = IOUtils.readInt(in, "Enter customer id: ", 0);
            Customer customer = customerService.getById(id);
            if (customer == null)
                IOUtils.printError("There is no customer with this id!");
            else {
                Customer updatedCustomer = customerService.update(customerService.readCustomer(in, customer));
                if (updatedCustomer == null)
                    IOUtils.printError("Update failed!");
                else
                    System.out.println("Customer updated successfully!");
            }
        });
        customersMenu.addOption("Delete customer", () -> {
            int id = IOUtils.readInt(in, "Enter customer id: ", 0);
            boolean result = customerService.delete(id);
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
            Driver driver = driverService.readDriver(in);
            driverService.create(driver);
            System.out.println("Driver added successfully!");
        });
        driversMenu.addOption("List all drivers", () -> {
            Driver[] drivers = driverService.getAll();
            if (drivers.length == 0)
                IOUtils.printError("There are no drivers in the database! Try creating one first.\n");
            else
                Arrays.stream(drivers).forEach(driverService::printDriverShort);
        });
        driversMenu.addOption("Get driver", () -> {
            Driver driver = driverService.getById(IOUtils.readInt(in, "Enter driver id: ", 0));
            if (driver == null)
                IOUtils.printError("There is no driver with this id!");
            else
                driverService.printDriver(driver);
        });
        driversMenu.addOption("Update driver", () -> {
            int id = IOUtils.readInt(in, "Enter driver id: ", 0);
            Driver driver = driverService.getById(id);
            if (driver == null)
                IOUtils.printError("There is no customer with this id!");
            else {
                Driver updatedDriver = driverService.update(driverService.readDriver(in, driver));
                if (updatedDriver == null)
                    IOUtils.printError("Update failed!");
                else
                    System.out.println("Driver updated successfully!");
            }
        });
        driversMenu.addOption("Delete driver", () -> {
            int id = IOUtils.readInt(in, "Enter driver id: ", 0);
            boolean result = driverService.delete(id);
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
            Restaurant restaurant = restaurantService.readRestaurant(in);
            restaurantService.create(restaurant);
            System.out.println("Restaurant added successfully!");
        });
        restaurantsMenu.addOption("List all restaurants", () -> {
            Restaurant[] restaurants = restaurantService.getAll();
            if (restaurants.length == 0)
                IOUtils.printError("There are no restaurants in the database! Try creating one first.\n");
            else
                Arrays.stream(restaurants).forEach(restaurantService::printRestaurantShort);
        });
        restaurantsMenu.addOption("Get restaurant", () -> {
            Restaurant restaurant = restaurantService.getById(IOUtils.readInt(in, "Enter restaurant id: ", 0));
            if (restaurant == null)
                IOUtils.printError("There is no restaurant with this id!");
            else
                restaurantService.printRestaurant(restaurant);
        });
        restaurantsMenu.addOption("Update restaurant", () -> {
            int id = IOUtils.readInt(in, "Enter restaurant id: ", 0);
            Restaurant restaurant = restaurantService.getById(id);
            if (restaurant == null)
                IOUtils.printError("There is no restaurant with this id!");
            else {
                Restaurant updatedRestaurant = restaurantService.update(
                        restaurantService.readRestaurant(in, restaurant));
                if (updatedRestaurant == null)
                    IOUtils.printError("Update failed!");
                else
                    System.out.println("Restaurant updated successfully!");
            }
        });
        restaurantsMenu.addOption("Delete restaurant", () -> {
            int id = IOUtils.readInt(in, "Enter restaurant id: ", 0);
            boolean result = restaurantService.delete(id);
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
            Product product = productService.readProduct(in);
            productService.create(product);
            System.out.println("Product added successfully!");
        });
        productsMenu.addOption("List all products", () -> {
            Product[] products = productService.getAll();
            if (products.length == 0)
                IOUtils.printError("There are no products in the database! Try creating one first.");
            else
                Arrays.stream(products).forEach(productService::printProductShort);
        });
        productsMenu.addOption("List all products from a restaurant", () -> {
            Integer restaurantId = restaurantService.readRestaurantId(in);
            Product[] products = productService.getAllByRestaurantId(restaurantId);
            if (products.length == 0)
                IOUtils.printError("There are no products in the database! Try creating one first.");
            else
                Arrays.stream(products).forEach(productService::printProductShort);
        });
        productsMenu.addOption("Get product", () -> {
            Product product = productService.getById(IOUtils.readInt(in, "Enter product id: ", 0));
            if (product == null)
                IOUtils.printError("There is no product with this id!");
            else
                productService.printProduct(product);
        });
        productsMenu.addOption("Update product", () -> {
            int id = IOUtils.readInt(in, "Enter product id: ", 0);
            Product product = productService.getById(id);
            if (product == null)
                IOUtils.printError("There is no product with this id!");
            else {
                Product updatedProduct = productService.update(productService.readProduct(in, product));
                if (updatedProduct == null)
                    IOUtils.printError("Update failed!");
                else
                    System.out.println("Product updated successfully!");
            }
        });
        productsMenu.addOption("Delete product", () -> {
            int id = IOUtils.readInt(in, "Enter product id: ", 0);
            boolean result = productService.delete(id);
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
            Order order = orderService.readOrder(in);
            orderService.create(order);
            System.out.println("Order added successfully!");
        });
        ordersMenu.addOption("List all orders", () -> {
            Order[] orders = orderService.getAll();
            if (orders.length == 0)
                IOUtils.printError("There are no orders in the database! Try creating one first.");
            else
                Arrays.stream(orders).forEach(orderService::printOrderShort);
        });
        ordersMenu.addOption("Get order", () -> {
            Order order = orderService.getById(IOUtils.readInt(in, "Enter order id: ", 0));
            if (order == null)
                IOUtils.printError("There is no order with this id!");
            else
                orderService.printOrder(order);
        });
        ordersMenu.addOption("Delete order", () -> {
            int id = IOUtils.readInt(in, "Enter order id: ", 0);
            boolean result = orderService.delete(id);
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

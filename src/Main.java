import models.Restaurant;
import services.RestaurantService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

//        CustomerService customerService = new CustomerService();
//        Customer customer = customerService.createCustomer(in);
//        customerService.printCustomer(customer);
//
//        DriverService driverService = new DriverService();
//        Driver driver = driverService.createDriver(in);
//        driverService.printDriver(driver);

        RestaurantService restaurantService = new RestaurantService();
        Restaurant restaurant = restaurantService.readRestaurant(in);
        restaurantService.printRestaurant(restaurant);
    }
}
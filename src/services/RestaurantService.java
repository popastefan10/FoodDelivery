package services;

import models.Restaurant;
import utils.IOUtils;

import java.util.Scanner;

public class RestaurantService extends UserService {
    public Restaurant readRestaurant(Scanner in) {
        System.out.println("Creating a new restaurant...");
        Restaurant restaurant = new Restaurant();

        super.readUser(in, restaurant);

        restaurant.setName(IOUtils.readString(in, "Name: ", 1));
        restaurant.setAddress(IOUtils.readString(in, "Address: ", 1));
        restaurant.setStartOfWorkingHours(IOUtils.readLocalTime(in, "Start of working hours (in HH:mm:ss format): ", 1));
        restaurant.setEndOfWorkingHours(IOUtils.readLocalTime(in, "End of working hours (in HH:mm:ss format): ", 1));

        return restaurant;
    }

    public void printRestaurant(Restaurant restaurant) {
        super.printUser(restaurant);
        System.out.println("\tName: " + restaurant.getName());
        System.out.println("\tAddress: " + restaurant.getAddress());
        System.out.println("\tStart of working hours: " + restaurant.getStartOfWorkingHours());
        System.out.println("\tEnd of working hours: " + restaurant.getEndOfWorkingHours());
    }
}

package services;

import models.Restaurant;
import utils.IOUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;

public class RestaurantService extends UserService {
    Map<UUID, Restaurant> restaurantsMap = new HashMap<UUID, Restaurant>();

    public Restaurant readRestaurant(Scanner in) {
        System.out.println("Creating a new restaurant...");
        Restaurant restaurant = new Restaurant();

        super.readUser(in, restaurant);

        restaurant.setName(IOUtils.readString(in, "Name: ", 1));
        restaurant.setAddress(IOUtils.readString(in, "Address: ", 1));
        restaurant.setStartOfWorkingHours(
                IOUtils.readLocalTime(in, "Start of working hours (in HH:mm:ss format): ", 1));
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

    public void addRestaurant(Restaurant restaurant) {
        restaurantsMap.put(restaurant.getId(), restaurant);
    }

    public Restaurant[] getRestaurants() {
        return restaurantsMap.values().toArray(new Restaurant[0]);
    }
}

package services;

import models.Restaurant;
import utils.IOUtils;

import java.util.*;

public class RestaurantService {
    private static final Map<Integer, Restaurant> restaurantsMap = new HashMap<>();

    public static Restaurant readRestaurant(Scanner in) {
        System.out.println("Creating a new restaurant...");
        Restaurant restaurant = new Restaurant();
        restaurant.setEmail(IOUtils.readString(in, "Email: ", 1));
        restaurant.setPhoneNumber(IOUtils.readString(in, "Phone number: ", 1));
        restaurant.setName(IOUtils.readString(in, "Name: ", 1));
        restaurant.setAddress(IOUtils.readString(in, "Address: ", 1));
        restaurant.setStartOfWorkingHours(
                IOUtils.readLocalTime(in, "Start of working hours (in HH:mm:ss format): ", 1));
        restaurant.setEndOfWorkingHours(IOUtils.readLocalTime(in, "End of working hours (in HH:mm:ss format): ", 1));

        return restaurant;
    }

    public static void printRestaurant(Restaurant restaurant) {
        System.out.println("Restaurant with id " + restaurant.getId() + ":");
        System.out.println("\tEmail: " + restaurant.getEmail());
        System.out.println("\tPhone number: " + restaurant.getPhoneNumber());
        System.out.println("\tName: " + restaurant.getName());
        System.out.println("\tAddress: " + restaurant.getAddress());
        System.out.println("\tStart of working hours: " + restaurant.getStartOfWorkingHours());
        System.out.println("\tEnd of working hours: " + restaurant.getEndOfWorkingHours());
    }

    public static void printRestaurantShort(Restaurant restaurant) {
        System.out.printf("\t%s %s, %s%n", restaurant.getId(), restaurant.getName(), restaurant.getAddress());
    }

    public static void addRestaurant(Restaurant restaurant) {
        restaurantsMap.put(restaurant.getId(), restaurant);
    }

    public static Restaurant[] getRestaurants() {
        return restaurantsMap.values().toArray(new Restaurant[0]);
    }

    public static Integer readRestaurantId(Scanner in) {
        System.out.println("Here are all available restaurants:");
        Arrays.stream(getRestaurants()).forEach(RestaurantService::printRestaurantShort);
        System.out.println();

        while (true) {
            Integer restaurantId = IOUtils.readInt(in, "Choose a restaurant id: ", 0);
            if (restaurantsMap.containsKey(restaurantId))
                return restaurantId;
            System.out.println(IOUtils.ANSI_RED + "There is no restaurant with this id!" + IOUtils.ANSI_RESET);
        }
    }
}

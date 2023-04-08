package services;

import models.Product;
import models.Restaurant;
import utils.IOUtils;

import java.util.*;

public class RestaurantService extends UserService {
    private static final Map<UUID, Restaurant> restaurantsMap = new HashMap<UUID, Restaurant>();

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
        System.out.println("Restaurant with id " + restaurant.getId().toString() + ":");
        super.printUser(restaurant);
        System.out.println("\tName: " + restaurant.getName());
        System.out.println("\tAddress: " + restaurant.getAddress());
        System.out.println("\tStart of working hours: " + restaurant.getStartOfWorkingHours());
        System.out.println("\tEnd of working hours: " + restaurant.getEndOfWorkingHours());
    }

    public static void printRestaurantShort(Restaurant restaurant) {
        System.out.printf("\t%s %s, %s%n", restaurant.getId(), restaurant.getName(), restaurant.getAddress());
    }

    public void addRestaurant(Restaurant restaurant) {
        restaurantsMap.put(restaurant.getId(), restaurant);
    }

    public static Restaurant[] getRestaurants() {
        return restaurantsMap.values().toArray(new Restaurant[0]);
    }

    public static UUID readRestaurantId(Scanner in) {
        System.out.println("Here are all available restaurants:");
        Arrays.stream(getRestaurants()).forEach(RestaurantService::printRestaurantShort);
        System.out.println();

        while (true) {
            UUID restaurantId = IOUtils.readUUID(in, "Choose a restaurant id: ", 0);
            if (restaurantsMap.containsKey(restaurantId))
                return restaurantId;
            System.out.println(IOUtils.ANSI_RED + "There is no restaurant with this id!" + IOUtils.ANSI_RESET);
        }
    }
}

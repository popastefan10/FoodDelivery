package services;

import models.Restaurant;
import repositories.RestaurantRepository;
import utils.IOUtils;

import java.util.*;

public class RestaurantService {
    private static final RestaurantRepository restaurantRepository = RestaurantRepository.getInstance();

    public static Restaurant readRestaurant(Scanner in) {
        System.out.println("Creating a new restaurant...");
        Restaurant restaurant = new Restaurant();
        restaurant.setEmail(IOUtils.readString(in, "Email: ", 1));
        restaurant.setPhoneNumber(IOUtils.readString(in, "Phone number: ", 1));
        restaurant.setName(IOUtils.readString(in, "Name: ", 1));
        restaurant.setAddress(IOUtils.readString(in, "Address: ", 1));
        restaurant.setRating(IOUtils.readFloat(in, "Rating: ", 1));

        return restaurant;
    }

    public static Restaurant readRestaurant(Scanner in, Restaurant defaultRestaurant) {
        System.out.println("Creating a new restaurant...");
        Restaurant restaurant = new Restaurant();
        restaurant.setId(defaultRestaurant.getId());

        restaurant.setEmail(IOUtils.readString(in, "Email", defaultRestaurant.getEmail(), 1));
        restaurant.setPhoneNumber(IOUtils.readString(in, "Phone number", defaultRestaurant.getPhoneNumber(), 1));
        restaurant.setName(IOUtils.readString(in, "Name", defaultRestaurant.getName(), 1));
        restaurant.setAddress(IOUtils.readString(in, "Address", defaultRestaurant.getAddress(), 1));
        restaurant.setRating(IOUtils.readFloat(in, "Rating", defaultRestaurant.getRating(), 1));

        return restaurant;
    }

    public static void printRestaurant(Restaurant restaurant) {
        System.out.println("Restaurant with id " + restaurant.getId() + ":");
        System.out.println("\tEmail: " + restaurant.getEmail());
        System.out.println("\tPhone number: " + restaurant.getPhoneNumber());
        System.out.println("\tName: " + restaurant.getName());
        System.out.println("\tAddress: " + restaurant.getAddress());
        System.out.println("\tRating: " + restaurant.getRating());
    }

    public static void printRestaurantShort(Restaurant restaurant) {
        System.out.printf("\t%s %s, %s%n", restaurant.getId(), restaurant.getName(), restaurant.getAddress());
    }

    public static Restaurant create(Restaurant restaurant) {
        return restaurantRepository.create(restaurant);
    }

    public static Restaurant[] getAll() {
        return restaurantRepository.getAll().values().toArray(new Restaurant[0]);
    }

    public static Restaurant getById(Integer id) {
        return restaurantRepository.getById(id);
    }

    public static Restaurant update(Restaurant restaurant) {
        return restaurantRepository.update(restaurant);
    }

    public static boolean delete(Integer id) {
        return restaurantRepository.delete(id);
    }

    public static Integer readRestaurantId(Scanner in) {
        Map<Integer, Restaurant> restaurants = restaurantRepository.getAll();
        System.out.println("Here are all available restaurants:");
        for (Restaurant restaurant : restaurants.values())
            printRestaurantShort(restaurant);
        System.out.println();

        while (true) {
            Integer restaurantId = IOUtils.readInt(in, "Choose a restaurant id: ", 0);
            if (restaurants.containsKey(restaurantId))
                return restaurantId;
            System.out.println(IOUtils.ANSI_RED + "There is no restaurant with this id!" + IOUtils.ANSI_RESET);
        }
    }
}

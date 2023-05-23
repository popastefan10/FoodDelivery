package services;

import models.Restaurant;
import repositories.RestaurantRepository;
import utils.IOUtils;

import java.util.*;

public class RestaurantService {
    private static RestaurantService instance = null;
    private static final RestaurantRepository restaurantRepository = RestaurantRepository.getInstance();
    private static final AuditService auditService = AuditService.getInstance();

    private RestaurantService() {
    }

    public static RestaurantService getInstance() {
        if (instance == null)
            instance = new RestaurantService();
        return instance;
    }

    public Restaurant readRestaurant(Scanner in) {
        System.out.println("Creating a new restaurant...");
        Restaurant restaurant = new Restaurant();
        restaurant.setEmail(IOUtils.readString(in, "Email: ", 1));
        restaurant.setPhoneNumber(IOUtils.readString(in, "Phone number: ", 1));
        restaurant.setName(IOUtils.readString(in, "Name: ", 1));
        restaurant.setAddress(IOUtils.readString(in, "Address: ", 1));
        restaurant.setRating(IOUtils.readFloat(in, "Rating: ", 1));

        return restaurant;
    }

    public Restaurant readRestaurant(Scanner in, Restaurant defaultValue) {
        System.out.println("Creating a new restaurant...");
        Restaurant restaurant = new Restaurant();
        restaurant.setId(defaultValue.getId());

        restaurant.setEmail(IOUtils.readString(in, "Email", defaultValue.getEmail(), 1));
        restaurant.setPhoneNumber(IOUtils.readString(in, "Phone number", defaultValue.getPhoneNumber(), 1));
        restaurant.setName(IOUtils.readString(in, "Name", defaultValue.getName(), 1));
        restaurant.setAddress(IOUtils.readString(in, "Address", defaultValue.getAddress(), 1));
        restaurant.setRating(IOUtils.readFloat(in, "Rating", defaultValue.getRating(), 1));

        return restaurant;
    }

    public Integer readRestaurantId(Scanner in) {
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

    public void printRestaurant(Restaurant restaurant) {
        System.out.println("Restaurant with id " + restaurant.getId() + ":");
        System.out.println("\tEmail: " + restaurant.getEmail());
        System.out.println("\tPhone number: " + restaurant.getPhoneNumber());
        System.out.println("\tName: " + restaurant.getName());
        System.out.println("\tAddress: " + restaurant.getAddress());
        System.out.println("\tRating: " + restaurant.getRating());
    }

    public void printRestaurantShort(Restaurant restaurant) {
        System.out.printf("\t%s %s, %s%n", restaurant.getId(), restaurant.getName(), restaurant.getAddress());
    }

    public Restaurant create(Restaurant restaurant) {
        auditService.logAction("restaurant_create");
        return restaurantRepository.create(restaurant);
    }

    public Restaurant[] getAll() {
        auditService.logAction("restaurant_get_all");
        return restaurantRepository.getAll().values().toArray(new Restaurant[0]);
    }

    public Restaurant getById(Integer id) {
        auditService.logAction("restaurant_get_by_id");
        return restaurantRepository.getById(id);
    }

    public Restaurant update(Restaurant restaurant) {
        auditService.logAction("restaurant_update");
        return restaurantRepository.update(restaurant);
    }

    public boolean delete(Integer id) {
        auditService.logAction("restaurant_delete");
        return restaurantRepository.delete(id);
    }
}

package services;

import models.Product;
import models.Restaurant;
import utils.IOUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;

public class ProductService {
    private static final Map<UUID, Product> productsMap = new HashMap<>();

    public static Product readProduct(Scanner in) {
        System.out.println("Creating a new product...");
        Product product = new Product();

        UUID restaurantId = RestaurantService.readRestaurantId(in);
        product.setRestaurantId(restaurantId);

        product.setName(IOUtils.readString(in, "Name: ", 1));
        product.setQuantity(IOUtils.readInt(in, "Quantity: ", 1));
        product.setMeasurementUnit(IOUtils.readString(in, "Measurement unit: ", 1));
        product.setPrice(IOUtils.readFloat(in, "Price: ", 1));

        return product;
    }

    public static void printProduct(Product product) {
        System.out.println("Product with id " + product.getId().toString() + ":");
        System.out.println("\tRestaurant id: " + product.getRestaurantId());
        System.out.println("\tName: " + product.getName());
        System.out.println("\tQuantity: " + product.getQuantity());
        System.out.println("\tMeasurement unit: " + product.getMeasurementUnit());
        System.out.println("\tPrice: " + product.getPrice());
    }

    public static void printProductShort(Product product) {
        System.out.printf("\t%s %s, %d %s, %f\n", product.getId().toString(), product.getName(),
                          product.getQuantity(), product.getMeasurementUnit(), product.getPrice());
    }

    public static void addProduct(Product product) {
        productsMap.put(product.getId(), product);
    }

    public static Product[] getProducts() {
        return productsMap.values().toArray(new Product[0]);
    }
}

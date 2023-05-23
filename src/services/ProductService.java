package services;

import models.Product;
import repositories.ProductRepository;
import utils.IOUtils;

import java.util.Scanner;

public class ProductService {
    public static final ProductRepository productRepository = ProductRepository.getInstance();

    public static Product readProduct(Scanner in) {
        System.out.println("Creating a new product...");
        Product product = new Product();

        Integer restaurantId = RestaurantService.readRestaurantId(in);
        product.setRestaurantId(restaurantId);
        product.setName(IOUtils.readString(in, "Name: ", 1));
        product.setQuantity(IOUtils.readFloat(in, "Quantity: ", 1));
        product.setMeasurementUnit(IOUtils.readString(in, "Measurement unit: ", 1));
        product.setPrice(IOUtils.readFloat(in, "Price: ", 1));

        return product;
    }

    public static Product readProduct(Scanner in, Product defaultValue) {
        System.out.println("Creating a new product...");
        Product product = new Product();
        product.setId(defaultValue.getId());
        product.setRestaurantId(defaultValue.getRestaurantId());

        product.setName(IOUtils.readString(in, "Name", defaultValue.getName(), 1));
        product.setQuantity(IOUtils.readFloat(in, "Quantity", defaultValue.getQuantity(), 1));
        product.setMeasurementUnit(IOUtils.readString(in, "Measurement unit", defaultValue.getMeasurementUnit(), 1));
        product.setPrice(IOUtils.readFloat(in, "Price", defaultValue.getPrice(), 1));

        return product;
    }

    public static void printProduct(Product product) {
        System.out.println("Product with id " + product.getId() + ":");
        System.out.println("\tRestaurant id: " + product.getRestaurantId());
        System.out.println("\tName: " + product.getName());
        System.out.println("\tQuantity: " + product.getQuantity());
        System.out.println("\tMeasurement unit: " + product.getMeasurementUnit());
        System.out.println("\tPrice: " + product.getPrice());
    }

    public static void printProductShort(Product product) {
        System.out.printf("\t%s %s %.1f %s, %.2f lei\n", product.getId(), product.getName(), product.getQuantity(),
                          product.getMeasurementUnit(), product.getPrice());
    }

    public static Product create(Product product) {
        return productRepository.create(product);
    }

    public static Product[] getAll() {
        return productRepository.getAll().values().toArray(new Product[0]);
    }

    public static Product getById(Integer id) {
        return productRepository.getById(id);
    }

    public static Product update(Product product) {
        return productRepository.update(product);
    }

    public static boolean delete(Integer id) {
        return productRepository.delete(id);
    }
}

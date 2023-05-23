package services;

import models.Product;
import repositories.ProductRepository;
import utils.IOUtils;

import java.util.Map;
import java.util.Scanner;

public class ProductService {
    public static final ProductRepository productRepository = ProductRepository.getInstance();
    private static final AuditService auditService = AuditService.getInstance();

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

    public static Integer readProductId(Scanner in, Integer restaurantId) {
        Map<Integer, Product> products = productRepository.getAllByRestaurantId(restaurantId);
        System.out.println("Here are all available products:");
        for (Product product : products.values())
            printProductShort(product);
        System.out.println();

        while (true) {
            Integer productId = IOUtils.readInt(in, "Choose a product id: ", 0);
            if (products.containsKey(productId))
                return productId;
            System.out.println(IOUtils.ANSI_RED + "There is no product with this id!" + IOUtils.ANSI_RESET);
        }
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
        System.out.printf("\t%s %s (%.1f %s), %.2f lei\n", product.getId(), product.getName(), product.getQuantity(),
                          product.getMeasurementUnit(), product.getPrice());
    }

    public static Product create(Product product) {
        auditService.logAction("product_create");
        return productRepository.create(product);
    }

    public static Product[] getAll() {
        auditService.logAction("product_get_all");
        return productRepository.getAll().values().toArray(new Product[0]);
    }

    public static Product[] getAllByRestaurantId(Integer restaurantId) {
        auditService.logAction("product_get_all_by_restaurant_id");
        return productRepository.getAllByRestaurantId(restaurantId).values().toArray(new Product[0]);
    }

    public static Product getById(Integer id) {
        auditService.logAction("product_get_by_id");
        return productRepository.getById(id);
    }

    public static Product update(Product product) {
        auditService.logAction("product_update");
        return productRepository.update(product);
    }

    public static boolean delete(Integer id) {
        auditService.logAction("product_delete");
        return productRepository.delete(id);
    }
}

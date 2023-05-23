package services;

import models.Customer;
import models.Driver;
import models.Order;
import models.Restaurant;
import repositories.OrderItemRepository;
import repositories.OrderRepository;
import utils.IOUtils;

import java.util.Scanner;

public class OrderService {
    private final static OrderRepository orderRepository = OrderRepository.getInstance();
    private final static OrderItemRepository orderItemRepository = OrderItemRepository.getInstance();

    public static Order readOrder(Scanner in) {
        System.out.println("Creating a new order...");
        Order order = new Order(CustomerService.readCustomerId(in), RestaurantService.readRestaurantId(in),
                                DriverService.readDriverId(in), Order.OrderStatus.PLACED);

        System.out.println("Adding items to order...");
        do {
            Integer productId = ProductService.readProductId(in, order.getRestaurantId());
            Integer quantity = IOUtils.readInt(in, "Quantity", 1, 1);
            order.addProduct(productId, quantity);
        } while (IOUtils.readBoolean(in, "Add another product?", 1));

        return order;
    }

    public static void printOrder(Order order) {
        Customer customer = CustomerService.getById(order.getCustomerId());
        Restaurant restaurant = RestaurantService.getById(order.getRestaurantId());
        Driver driver = DriverService.getById(order.getDriverId());

        System.out.println("Order #id" + customer.getId() + ":");
        System.out.println("\tCustomer: " + customer.getFullName());
        System.out.println("\tRestaurant: " + restaurant.getName());
        System.out.println("\tDriver: " + driver.getFullName());
        System.out.println("\tStatus: " + order.getStatus());
        System.out.println("\tItems:");
        for (var item : order.getItems().values())
            OrderItemService.printOrderItemShort(item);
    }

    public static void printOrderShort(Order order) {
        Customer customer = CustomerService.getById(order.getCustomerId());
        Restaurant restaurant = RestaurantService.getById(order.getRestaurantId());
        System.out.printf("\t#%d: placed by %s at %s, %d produs(e), %s\n", order.getId(),
                          customer.getFullName(), restaurant.getName(), order.getNumberOfProducts(),
                          order.getStatus());
    }

    public static Order create(Order order) {
        var createdOrder = orderRepository.create(order);
        for (var item : order.getItems().values()) {
            item.setOrderId(order.getId());
            var createdItem = orderItemRepository.create(item);
            createdOrder.addItem(createdItem);
        }

        return createdOrder;
    }

    public static Order[] getAll() {
        return orderRepository.getAll().values().toArray(new Order[0]);
    }

    public static Order getById(Integer id) {
        return orderRepository.getById(id);
    }

    public static Order update(Order order) {
        return orderRepository.update(order);
    }

    public static boolean delete(Integer id) {
        return orderRepository.delete(id);
    }
}

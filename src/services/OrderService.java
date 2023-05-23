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
    private static OrderService instance = null;
    private final OrderRepository orderRepository = OrderRepository.getInstance();
    private final OrderItemRepository orderItemRepository = OrderItemRepository.getInstance();
    private final AuditService auditService = AuditService.getInstance();
    private final CustomerService customerService = CustomerService.getInstance();
    private final DriverService driverService = DriverService.getInstance();
    private final OrderItemService orderItemService = OrderItemService.getInstance();
    private final ProductService productService = ProductService.getInstance();
    private final RestaurantService restaurantService = RestaurantService.getInstance();

    private OrderService() {
    }

    public static OrderService getInstance() {
        if (instance == null)
            instance = new OrderService();
        return instance;
    }

    public Order readOrder(Scanner in) {
        System.out.println("Creating a new order...");
        Order order = new Order(customerService.readCustomerId(in), restaurantService.readRestaurantId(in),
                                driverService.readDriverId(in), Order.OrderStatus.PLACED);

        System.out.println("Adding items to order...");
        do {
            Integer productId = productService.readProductId(in, order.getRestaurantId());
            Integer quantity = IOUtils.readInt(in, "Quantity", 1, 1);
            order.addProduct(productId, quantity);
        } while (IOUtils.readBoolean(in, "\nAdd another product?", 0));

        return order;
    }

    public void printOrder(Order order) {
        Customer customer = customerService.getById(order.getCustomerId());
        Restaurant restaurant = restaurantService.getById(order.getRestaurantId());
        Driver driver = driverService.getById(order.getDriverId());

        System.out.println("Order #" + customer.getId() + ":");
        System.out.println("\tCustomer: " + customer.getFullName());
        System.out.println("\tRestaurant: " + restaurant.getName());
        System.out.println("\tDriver: " + driver.getFullName());
        System.out.println("\tStatus: " + order.getStatus());
        System.out.println("\tItems:");
        for (var item : order.getItems().values())
            orderItemService.printOrderItemShort(item);
        System.out.println("\tTotal: " + getTotalPrice(order) + " RON");
    }

    public void printOrderShort(Order order) {
        Customer customer = customerService.getById(order.getCustomerId());
        Restaurant restaurant = restaurantService.getById(order.getRestaurantId());
        //noinspection SpellCheckingInspection
        System.out.printf("\t#%d: placed by %s at %s, %d produs(e), %s\n", order.getId(),
                          customer.getFullName(), restaurant.getName(), order.getNumberOfProducts(),
                          order.getStatus());
    }

    public Order create(Order order) {
        auditService.logAction("order_create");
        var createdOrder = orderRepository.create(order);
        for (var item : order.getItems().values()) {
            item.setOrderId(order.getId());
            var createdItem = orderItemRepository.create(item);
            createdOrder.addItem(createdItem);
        }

        return createdOrder;
    }

    public Order[] getAll() {
        auditService.logAction("order_get_all");
        return orderRepository.getAll().values().toArray(new Order[0]);
    }

    public Order getById(Integer id) {
        auditService.logAction("order_get_by_id");
        return orderRepository.getById(id);
    }

    public Order update(Order order) {
        auditService.logAction("order_update");
        return orderRepository.update(order);
    }

    public boolean delete(Integer id) {
        auditService.logAction("order_delete");
        return orderRepository.delete(id);
    }

    public Float getTotalPrice(Order order) {
        Float totalPrice = 0f;
        for (var item : order.getItems().values())
            totalPrice += orderItemService.getPrice(item);
        return totalPrice;
    }
}

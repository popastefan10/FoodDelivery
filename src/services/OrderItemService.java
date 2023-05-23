package services;

import models.OrderItem;
import models.Product;
import repositories.OrderItemRepository;

public class OrderItemService {
    private final static OrderItemRepository orderItemRepository = OrderItemRepository.getInstance();

    public static void printOrderItem(OrderItem orderItem) {
        Product product = ProductService.getById(orderItem.getProductId());
        System.out.println("Order item #" + orderItem.getId() + ":");
        ProductService.printProductShort(product);
        System.out.println("\tQuantity: " + orderItem.getQuantity());
    }

    public static void printOrderItemShort(OrderItem orderItem) {
        Product product = ProductService.getById(orderItem.getProductId());
        System.out.printf("\t%s\tx%d\n", product.getName(), orderItem.getQuantity());
    }

    public static OrderItem create(OrderItem orderItem) {
        return orderItemRepository.create(orderItem);
    }

    public static OrderItem[] getAll() {
        return orderItemRepository.getAll().values().toArray(new OrderItem[0]);
    }

    public static OrderItem[] getAllByOrderId(Integer orderId) {
        return orderItemRepository.getAllByOrderId(orderId).values().toArray(new OrderItem[0]);
    }

    public static OrderItem getById(Integer id) {
        return orderItemRepository.getById(id);
    }

    public static OrderItem update(OrderItem orderItem) {
        return orderItemRepository.update(orderItem);
    }

    public static boolean delete(Integer id) {
        return orderItemRepository.delete(id);
    }
}

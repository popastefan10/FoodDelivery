package services;

import models.OrderItem;
import models.Product;
import repositories.OrderItemRepository;

public class OrderItemService {
    private final static OrderItemRepository orderItemRepository = OrderItemRepository.getInstance();
    private final static AuditService auditService = AuditService.getInstance();

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
        auditService.logAction("order-item_create");
        return orderItemRepository.create(orderItem);
    }

    public static OrderItem[] getAll() {
        auditService.logAction("order-item_get_all");
        return orderItemRepository.getAll().values().toArray(new OrderItem[0]);
    }

    public static OrderItem[] getAllByOrderId(Integer orderId) {
        auditService.logAction("order-item_get_all_by_order_id");
        return orderItemRepository.getAllByOrderId(orderId).values().toArray(new OrderItem[0]);
    }

    public static OrderItem getById(Integer id) {
        auditService.logAction("order-item_get_by_id");
        return orderItemRepository.getById(id);
    }

    public static OrderItem update(OrderItem orderItem) {
        auditService.logAction("order-item_update");
        return orderItemRepository.update(orderItem);
    }

    public static boolean delete(Integer id) {
        auditService.logAction("order-item_delete");
        return orderItemRepository.delete(id);
    }
}

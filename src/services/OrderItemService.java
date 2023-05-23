package services;

import models.OrderItem;
import models.Product;
import repositories.OrderItemRepository;

public class OrderItemService {
    private static OrderItemService instance = null;
    private final OrderItemRepository orderItemRepository = OrderItemRepository.getInstance();
    private final AuditService auditService = AuditService.getInstance();
    private final ProductService productService = ProductService.getInstance();

    private OrderItemService() {
    }

    public static OrderItemService getInstance() {
        if (instance == null)
            instance = new OrderItemService();
        return instance;
    }

    public void printOrderItem(OrderItem orderItem) {
        Product product = productService.getById(orderItem.getProductId());
        System.out.println("Order item #" + orderItem.getId() + ":");
        productService.printProductShort(product);
        System.out.println("\tQuantity: " + orderItem.getQuantity());
    }

    public void printOrderItemShort(OrderItem orderItem) {
        Product product = productService.getById(orderItem.getProductId());
        System.out.printf("\t%s\tx%d\n", product.getName(), orderItem.getQuantity());
    }

    public OrderItem create(OrderItem orderItem) {
        auditService.logAction("order-item_create");
        return orderItemRepository.create(orderItem);
    }

    public OrderItem[] getAll() {
        auditService.logAction("order-item_get_all");
        return orderItemRepository.getAll().values().toArray(new OrderItem[0]);
    }

    public OrderItem[] getAllByOrderId(Integer orderId) {
        auditService.logAction("order-item_get_all_by_order_id");
        return orderItemRepository.getAllByOrderId(orderId).values().toArray(new OrderItem[0]);
    }

    public OrderItem getById(Integer id) {
        auditService.logAction("order-item_get_by_id");
        return orderItemRepository.getById(id);
    }

    public OrderItem update(OrderItem orderItem) {
        auditService.logAction("order-item_update");
        return orderItemRepository.update(orderItem);
    }

    public boolean delete(Integer id) {
        auditService.logAction("order-item_delete");
        return orderItemRepository.delete(id);
    }

    public Float getPrice(OrderItem orderItem) {
        Product product = productService.getById(orderItem.getProductId());
        return product.getPrice() * orderItem.getQuantity();
    }
}

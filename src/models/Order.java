package models;

import java.util.HashMap;
import java.util.Map;

public class Order extends Entity {
    public enum OrderStatus {
        PLACED,
        IN_PREPARATION,
        READY_FOR_PICKUP,
        IN_DELIVERY,
        DELIVERED,
        CANCELED
    }

    private Integer customerId;
    private Integer restaurantId;
    private Integer driverId;
    private OrderStatus status;
    private Map<Integer, OrderItem> items = new HashMap<>();

    public Order() {
        super();
    }

    public Order(Integer customerId, Integer restaurantId, Integer driverId, OrderStatus status) {
        super();
        this.customerId = customerId;
        this.restaurantId = restaurantId;
        this.driverId = driverId;
        this.status = status;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public Integer getRestaurantId() {
        return restaurantId;
    }

    public Integer getDriverId() {
        return driverId;
    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Map<Integer, OrderItem> getItems() {
        return items;
    }

    public Integer getNumberOfProducts() {
        return items.values().stream().mapToInt(OrderItem::getQuantity).sum();
    }

    public void setItems(Map<Integer, OrderItem> items) {
        this.items = items;
    }

    public void addItem(OrderItem item) {
        items.put(item.getProductId(), item);
    }

    public void addProduct(Integer productId, Integer quantity) {
        if (items.containsKey(productId)) {
            OrderItem item = items.get(productId);
            item.setQuantity(item.getQuantity() + quantity);
        } else {
            items.put(productId, new OrderItem(getId(), productId, quantity));
        }
    }

    public OrderItem removeProduct(Integer productId) {
        return items.remove(productId);
    }
}

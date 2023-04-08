package models;

import java.util.UUID;

public class OrderProduct {
    private final UUID orderId;
    private final UUID productId;
    private final Integer quantity;

    public OrderProduct(UUID orderId, UUID productId, Integer quantity) {
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public UUID getProductId() {
        return productId;
    }

    public Integer getQuantity() {
        return quantity;
    }
}

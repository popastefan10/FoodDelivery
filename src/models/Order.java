package models;

import java.util.UUID;

public class Order extends Entity {
    public enum OrderStatus {
        PLACED(0),
        IN_PREPARATION(1),
        READY_FOR_PICKUP(2),
        IN_DELIVERY(3),
        DELIVERED(4);

        private int value;

        OrderStatus(int value) {
            this.value = value;
        }

        int getValue() {
            return value;
        }
    }

    private UUID id;
    private UUID customerId;
    private UUID restaurantId;
    private UUID driverId;
    private Basket basket;
    private OrderStatus status;

    public Order() {
        super();
    }

    public Order(UUID customerId, UUID restaurantId, Basket basket, OrderStatus status) {
        super();
        this.customerId = customerId;
        this.restaurantId = restaurantId;
        this.basket = basket;
        this.status = status;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public UUID getRestaurantId() {
        return restaurantId;
    }

    public UUID getDriverId() {
        return driverId;
    }

    public void setDriverId(UUID driverId) {
        this.driverId = driverId;
    }

    public Basket getBasket() {
        return basket;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        if (this.status == null || status.getValue() > this.status.getValue())
            this.status = status;
    }
}

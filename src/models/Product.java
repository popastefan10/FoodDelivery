package models;

import java.util.UUID;

public class Product extends Entity {
    private UUID restaurantId;
    private String name;
    private Integer quantity;
    private String unitLabel;
    private Float price;

    public Product() {
        super();
    }

    public Product(UUID restaurantId, String name, Integer quantity, String unitLabel, Float price) {
        super();
        this.restaurantId = restaurantId;
        this.name = name;
        this.quantity = quantity;
        this.unitLabel = unitLabel;
        this.price = price;
    }

    public UUID getRestaurantId() {
        return restaurantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getUnitLabel() {
        return unitLabel;
    }

    public void setUnitLabel(String unitLabel) {
        this.unitLabel = unitLabel;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}

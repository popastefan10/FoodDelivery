package models;

public class Product extends Entity {
    private Integer restaurantId;
    private String name;
    private Integer quantity;
    private String measurementUnit;
    private Float price;

    public Product() {
        super();
    }

    public Product(Integer restaurantId, String name, Integer quantity, String measurementUnit, Float price) {
        super();
        this.restaurantId = restaurantId;
        this.name = name;
        this.quantity = quantity;
        this.measurementUnit = measurementUnit;
        this.price = price;
    }

    public Integer getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
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

    public String getMeasurementUnit() {
        return measurementUnit;
    }

    public void setMeasurementUnit(String measurementUnit) {
        this.measurementUnit = measurementUnit;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}

package models;

import java.time.LocalTime;

public class Restaurant extends User {
    private String name;
    private String address;
    private LocalTime startOfWorkingHours;
    private LocalTime endOfWorkingHours;
    private Boolean availableForDelivery;

    public Restaurant() {
        super();
    }

    public Restaurant(String email, String phoneNumber, String name, String address, LocalTime startOfWorkingHours, LocalTime endOfWorkingHours, Boolean availableForDelivery) {
        super(email, phoneNumber);
        this.name = name;
        this.address = address;
        this.startOfWorkingHours = startOfWorkingHours;
        this.endOfWorkingHours = endOfWorkingHours;
        this.availableForDelivery = availableForDelivery;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalTime getStartOfWorkingHours() {
        return startOfWorkingHours;
    }

    public void setStartOfWorkingHours(LocalTime startOfWorkingHours) {
        this.startOfWorkingHours = startOfWorkingHours;
    }

    public LocalTime getEndOfWorkingHours() {
        return endOfWorkingHours;
    }

    public void setEndOfWorkingHours(LocalTime endOfWorkingHours) {
        this.endOfWorkingHours = endOfWorkingHours;
    }

    public Boolean getAvailableForDelivery() {
        return availableForDelivery;
    }

    public void setAvailableForDelivery(Boolean availableForDelivery) {
        this.availableForDelivery = availableForDelivery;
    }
}

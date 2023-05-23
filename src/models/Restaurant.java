package models;

public class Restaurant extends User {
    private String name;
    private String address;
    private Float rating;

    public Restaurant() {
        super();
    }

    public Restaurant(
            String email, String phoneNumber, String name, String address, Float rating
    ) {
        super(email, phoneNumber);
        this.name = name;
        this.address = address;
        this.rating = rating;
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

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }
}

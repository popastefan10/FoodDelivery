package models;

public class Driver extends Person {
    private float rating;

    public Driver() {
        super();
    }

    public Driver(String email, String phoneNumber, String firstName, String lastName, float rating) {
        super(email, phoneNumber, firstName, lastName);
        this.rating = rating;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
